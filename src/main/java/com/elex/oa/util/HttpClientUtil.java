package com.elex.oa.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.elex.oa.entity.core.CookieModel;
import com.elex.oa.entity.core.KeyValEnt;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class HttpClientUtil {
    private static SSLContext _sslContext;

    public HttpClientUtil() {
    }

    private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        X509TrustManager trustManager = new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }
        };
        sc.init((KeyManager[])null, new TrustManager[]{trustManager}, (SecureRandom)null);
        return sc;
    }

    private static CloseableHttpClient createSSLClientDefault() {
        try {
            if(_sslContext == null) {
                _sslContext = createIgnoreVerifySSL();
            }

            SSLConnectionSocketFactory e = new SSLConnectionSocketFactory(_sslContext);
            return HttpClients.custom().setSSLSocketFactory(e).build();
        } catch (KeyManagementException var1) {
            var1.printStackTrace();
        } catch (NoSuchAlgorithmException var2) {
            var2.printStackTrace();
        }

        return HttpClients.createDefault();
    }

    public static String getFromUrl(String url, Map<String, String> params) throws Exception {
        CloseableHttpClient httpclient = createSSLClientDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        if(params != null) {
            Iterator uri = params.keySet().iterator();

            while(uri.hasNext()) {
                String httpget = (String)uri.next();
                String response = (String)params.get(httpget);
                uriBuilder.setParameter(httpget, response);
            }
        }

        URI uri1 = uriBuilder.build();
        HttpGet httpget1 = new HttpGet(uri1);
        CloseableHttpResponse response1 = httpclient.execute(httpget1);
        InputStream instream = null;

        String var10;
        try {
            HttpEntity entity = response1.getEntity();
            if(entity == null) {
                return null;
            }

            instream = entity.getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(instream, writer, "UTF-8");
            var10 = writer.toString();
        } finally {
            if(instream != null) {
                instream.close();
            }

            response1.close();
        }

        return var10;
    }

    public static String postFromUrl(String url, Map<String, String> params) throws Exception {
        ArrayList nvps = new ArrayList();
        Iterator keyIt = params.keySet().iterator();

        while(keyIt.hasNext()) {
            String key = (String)keyIt.next();
            String val = (String)params.get(key);
            nvps.add(new BasicNameValuePair(key, val));
        }

        return post(url, new UrlEncodedFormEntity(nvps));
    }

    public static String uploadFile(String url, String filepath, Map<String, String> fileMap) throws IOException {
        MultipartEntityBuilder builer = MultipartEntityBuilder.create();
        Iterator var4 = fileMap.entrySet().iterator();

        while(var4.hasNext()) {
            Entry entry = (Entry)var4.next();
            FileBody bin = new FileBody(new File(filepath + File.separator + (String)entry.getValue()));
            builer.addPart((String)entry.getKey(), bin);
        }

        return post(url, builer.build());
    }

    public static String uploadFile(String url, Map<String, String> fileMap) throws IOException {
        HashMap txtMap = new HashMap();
        String json = uploadFile(url, (Map)txtMap, fileMap);
        return json;
    }

    public static String uploadFile(String url, Map<String, String> txtMap, Map<String, String> fileMap) throws IOException {
        MultipartEntityBuilder builer = MultipartEntityBuilder.create();
        Iterator reqEntity;
        Entry entry;
        if(BeanUtil.isNotEmpty(fileMap)) {
            reqEntity = fileMap.entrySet().iterator();

            while(reqEntity.hasNext()) {
                entry = (Entry)reqEntity.next();
                FileBody bin = new FileBody(new File((String)entry.getValue()));
                builer.addPart((String)entry.getKey(), bin);
            }
        }

        if(BeanUtil.isNotEmpty(txtMap)) {
            reqEntity = txtMap.entrySet().iterator();

            while(reqEntity.hasNext()) {
                entry = (Entry)reqEntity.next();
                builer.addTextBody((String)entry.getKey(), (String)entry.getValue());
            }
        }

        HttpEntity reqEntity1 = builer.build();
        return post(url, reqEntity1);
    }

    public static String post(String url, HttpEntity reqEntity) throws IOException {
        return post(url, reqEntity, (Map)null);
    }

    public static String post(String url, HttpEntity reqEntity, Map<String, String> headerMap) throws IOException {
        CloseableHttpClient httpclient = createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(reqEntity);
        if(BeanUtil.isNotEmpty(headerMap)) {
            Iterator response = headerMap.entrySet().iterator();

            while(response.hasNext()) {
                Entry instream = (Entry)response.next();
                httpPost.addHeader((String)instream.getKey(), (String)instream.getValue());
            }
        }

        CloseableHttpResponse response1 = httpclient.execute(httpPost);
        InputStream instream1 = null;

        String var9;
        try {
            HttpEntity ex = response1.getEntity();
            if(ex == null) {
                return null;
            }

            instream1 = ex.getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(instream1, writer, "UTF-8");
            var9 = writer.toString();
        } catch (Exception var13) {
            var13.printStackTrace();
            return null;
        } finally {
            if(instream1 != null) {
                instream1.close();
            }

            response1.close();
        }

        return var9;
    }

/*
    public static void downLoad(String url, final String localFileName) throws IOException {
        downLoad(url, new IFileHandler() {
            public void handInputStream(HttpResponse response, InputStream is) throws IOException {
                File file = new File(localFileName);
                if(!file.exists()) {
                    file.createNewFile();
                }

                FileOutputStream out = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                boolean readLength = false;

                int readLength1;
                while((readLength1 = is.read(buffer)) > 0) {
                    byte[] bytes = new byte[readLength1];
                    System.arraycopy(buffer, 0, bytes, 0, readLength1);
                    out.write(bytes);
                }

                out.flush();
                out.close();
            }
        });
    }

    public static void downLoad(String url, IFileHandler handler) throws IOException {
        CloseableHttpClient httpclient = createSSLClientDefault();
        InputStream in = null;

        try {
            HttpGet e = new HttpGet(url);
            CloseableHttpResponse httpResponse = httpclient.execute(e);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();
            long length = entity.getContentLength();
            if(length > 0L) {
                handler.handInputStream(httpResponse, in);
                return;
            }
        } catch (IOException var13) {
            var13.printStackTrace();
            return;
        } catch (Exception var14) {
            var14.printStackTrace();
            return;
        } finally {
            if(in != null) {
                in.close();
            }

        }

    }
*/

    public static HttpClientUtil.HttpRtnModel postFromUrl(String url, String cookie, Map<String, String> params) throws Exception {
        HttpClientUtil.HttpRtnModel rtnModel = new HttpClientUtil().new HttpRtnModel();
        CloseableHttpClient httpclient = createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
        if(BeanUtil.isNotEmpty(params)) {
            ArrayList response = new ArrayList();
            Iterator instream = params.keySet().iterator();

            while(instream.hasNext()) {
                String entity = (String)instream.next();
                String headers = (String)params.get(entity);
                response.add(new BasicNameValuePair(entity, headers));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(response));
        }

        if(StringUtil.isNotEmpty(cookie)) {
            httpPost.addHeader("Cookie", cookie);
        }

        CloseableHttpResponse var17 = httpclient.execute(httpPost);
        InputStream var18 = null;

        try {
            rtnModel.setStatusCode(var17.getStatusLine().getStatusCode());
            HttpEntity var19 = var17.getEntity();
            Header[] var20 = var17.getAllHeaders();
            Header[] writer = var20;
            int var11 = var20.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                Header header = writer[var12];
                rtnModel.addHeader(header.getName(), header.getValue());
            }

            if(var19 != null) {
                var18 = var19.getContent();
                StringWriter var21 = new StringWriter();
                IOUtils.copy(var18, var21, "UTF-8");
                rtnModel.setContent(var21.toString());
            }
        } finally {
            if(var18 != null) {
                var18.close();
            }

            var17.close();
        }

        return rtnModel;
    }

    public static String postJson(String url, String jsonParams) throws Exception {
        StringEntity entity = new StringEntity(jsonParams, "utf-8");
        return post(url, entity);
    }

    public static void main(String[] args) throws Exception {
    }

    public class HttpRtnModel {
        private List<KeyValEnt> ents = new ArrayList();
        private String content = "";
        private int statusCode = 200;

        public HttpRtnModel() {
        }

        public void addHeader(String key, String val) {
            KeyValEnt ent = new KeyValEnt(key, val);
            this.ents.add(ent);
        }

        public List<KeyValEnt> getHeader(String key) {
            ArrayList rtnList = new ArrayList();
            Iterator var3 = this.ents.iterator();

            while(var3.hasNext()) {
                KeyValEnt ent = (KeyValEnt)var3.next();
                if(ent.getKey().equals(key)) {
                    rtnList.add(ent);
                }
            }

            return rtnList;
        }

        public List<KeyValEnt> getEnts() {
            return this.ents;
        }

        public void setEnts(List<KeyValEnt> ents) {
            this.ents = ents;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public List<CookieModel> getCookies() {
            ArrayList models = new ArrayList();
            List ents = this.getHeader("Set-Cookie");
            Iterator var3 = ents.iterator();

            while(var3.hasNext()) {
                KeyValEnt ent = (KeyValEnt)var3.next();
                String val = ent.getVal().toString();
                CookieModel model = this.getCookie(val);
                models.add(model);
            }

            return models;
        }

        private CookieModel getCookie(String str) {
            String[] aryTmp = str.split(";");
            String tmp = aryTmp[0];
            String[] aryCookie = tmp.split("=");
            CookieModel model = new CookieModel();
            model.setName(aryCookie[0]);
            if(aryCookie.length == 2) {
                model.setValue(aryCookie[1]);
            }

            return model;
        }

        public String getSessionId() {
            List list = this.getCookies();
            Iterator var2 = list.iterator();

            CookieModel model;
            do {
                if(!var2.hasNext()) {
                    return "";
                }

                model = (CookieModel)var2.next();
            } while(!"JSESSIONID".equals(model.getName()));

            return model.getValue();
        }
    }
}
