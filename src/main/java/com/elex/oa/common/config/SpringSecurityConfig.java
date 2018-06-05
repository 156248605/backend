package com.elex.oa.common.config;
import com.elex.oa.saweb.filter.CustomPwdEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import com.elex.oa.saweb.security.filter.AccessDecisionManagerImpl;
import com.elex.oa.saweb.security.provider.UserDetailsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity  //启动SpringSecurity过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Resource
    private UserDetailsProvider userDetailsProvider;

    @Resource
    private CustomPwdEncoder customPwdEncoder;
/*    @Bean
    public UserDetailsService UserDetailsProvider(){
        UserDetailsProvider userDetailsProvider = new UserDetailsProvider();
        return  userDetailsProvider;
    }

    @Bean
    public CustomPwdEncoder customPwdEncoderProvider(){


    }*/

     @Autowired
     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
         auth.userDetailsService(this.userDetailsProvider).passwordEncoder(this.customPwdEncoder);
     }


    //该方法作用是代替之前的配置security:authentication-manager
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("eric").password("123456").authorities("ROLE_USER");
        auth.userDetailsService(UserDetailsProvider());

    }*/


    //该方法作用是代替之前的配置<security:http>
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/login")
                 .permitAll()
                 .antMatchers("/resources/**")
                 .permitAll()
                 .antMatchers("/styles/**")
                 .permitAll()
                 .antMatchers("/scripts/**")
                 .permitAll()
                 .antMatchers("/**")
                 .permitAll()
                 /*.fullyAuthenticated()*/
                 ;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
                .ignoring()
                .antMatchers(
                        HttpMethod.POST
                )

                // allow anonymous resource requests
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                )

                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");
    }
}
