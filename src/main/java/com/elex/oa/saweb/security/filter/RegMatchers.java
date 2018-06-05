package com.elex.oa.saweb.security.filter;
import com.elex.oa.util.BeanUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class RegMatchers {
    private List<Pattern> ingoreUrls = new ArrayList();

    public RegMatchers() {
    }

    public void setIngoreUrls(List<String> urls) {
        if(!BeanUtil.isEmpty(urls)) {
            Iterator var2 = urls.iterator();

            while(var2.hasNext()) {
                String url = (String)var2.next();
                Pattern regex = Pattern.compile(url, 106);
                this.ingoreUrls.add(regex);
            }

        }
    }

    public boolean isContainUrl(String requestUrl) {
        Iterator var2 = this.ingoreUrls.iterator();

        Matcher regexMatcher;
        do {
            if(!var2.hasNext()) {
                return false;
            }

            Pattern pattern = (Pattern)var2.next();
            regexMatcher = pattern.matcher(requestUrl);
        } while(!regexMatcher.find());

        return true;
    }
}
