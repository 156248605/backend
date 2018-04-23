package com.elex.oa.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.nustaq.serialization.FSTConfiguration;
/**
 *@author hugo.zhao
 *@since 2018/4/9 15:12
*/
public class FileUtil {
    static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public FileUtil() {
    }

    public static String getFileExt(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return pos > -1?fileName.substring(pos + 1).toLowerCase():"";
    }

    public static void checkAndCreatePath(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

    }

    public static void zipFile(File inputFile, ZipOutputStream outputStream) {
        if(inputFile.exists()) {
            try {
                FileInputStream e = new FileInputStream(inputFile);
                BufferedInputStream bins = new BufferedInputStream(e, 512);
                ZipEntry entry = new ZipEntry(inputFile.getName());
                outputStream.putNextEntry(entry);
                byte[] buffer = new byte[512];

                int nNumber;
                while((nNumber = bins.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, nNumber);
                }

                bins.close();
                e.close();
            } catch (Exception var7) {
                var7.printStackTrace();
            }

        }
    }

    public static void zipFile(InputStream is, String fileName, ZipOutputStream outputStream) {
        try {
            BufferedInputStream e = new BufferedInputStream(is, 512);
            ZipEntry entry = new ZipEntry(fileName);
            outputStream.putNextEntry(entry);
            byte[] buffer = new byte[512];

            int nNumber;
            while((nNumber = e.read(buffer)) != -1) {
                outputStream.write(buffer, 0, nNumber);
            }

            e.close();
            is.close();
            outputStream.closeEntry();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception {
        if(!StringUtil.isEmpty(zipFilePath) && !StringUtil.isEmpty(unzipFilePath)) {
            File zipFile = new File(zipFilePath);
            if(includeZipFileName) {
                String unzipFileDir = zipFile.getName();
                if(StringUtil.isNotEmpty(unzipFileDir)) {
                    unzipFileDir = unzipFileDir.substring(0, unzipFileDir.lastIndexOf("."));
                }

                unzipFilePath = unzipFilePath + File.separator + unzipFileDir;
            }

            File unzipFileDir1 = new File(unzipFilePath);
            if(!unzipFileDir1.exists() || !unzipFileDir1.isDirectory()) {
                unzipFileDir1.mkdirs();
            }

            ZipEntry entry = null;
            String entryFilePath = null;
            String entryDirPath = null;
            File entryFile = null;
            File entryDir = null;
            boolean index = false;
            boolean count = false;
            short bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            ZipFile zip = new ZipFile(zipFile);
            Enumeration entries = zip.entries();

            while(true) {
                do {
                    if(!entries.hasMoreElements()) {
                        zip.close();
                        return;
                    }

                    entry = (ZipEntry)entries.nextElement();
                } while(entry.isDirectory());

                entryFilePath = unzipFilePath + File.separator + entry.getName();
                int index1 = entryFilePath.lastIndexOf(File.separator) > entryFilePath.lastIndexOf("/")?entryFilePath.lastIndexOf(File.separator):entryFilePath.lastIndexOf("/");
                if(index1 != -1) {
                    entryDirPath = entryFilePath.substring(0, index1);
                } else {
                    entryDirPath = "";
                }

                entryDir = new File(entryDirPath);
                if(!entryDir.exists() || !entryDir.isDirectory()) {
                    entryDir.mkdirs();
                }

                entryFile = new File(entryFilePath);
                if(entryFile.exists()) {
                    entryFile.delete();
                }

                try {
                    bos = new BufferedOutputStream(new FileOutputStream(entryFile));
                    bis = new BufferedInputStream(zip.getInputStream(entry));

                    int count1;
                    while((count1 = bis.read(buffer, 0, bufferSize)) != -1) {
                        bos.write(buffer, 0, count1);
                    }

                    bos.flush();
                    bos.close();
                    bis.close();
                } catch (Exception var19) {
                    var19.printStackTrace();
                }
            }
        } else {
            throw new Exception("PARAMETER_IS_NULL");
        }
    }

    public static Object cloneObject(Object obj) throws Exception {
        byte[] bytes = conf.asByteArray(obj);
        return conf.asObject(bytes);
    }

    public static byte[] objToBytes(Object obj) throws Exception {
        byte[] bytes = conf.asByteArray(obj);
        return bytes;
    }

    public static Object bytesToObject(byte[] bytes) throws Exception {
        return conf.asObject(bytes);
    }

    public static void writeFile(String fileName, String content) {
        writeFile(fileName, content, "utf-8");
    }

    public static void writeFile(String fileName, String content, String charset) {
        BufferedWriter out = null;

        try {
            createFolder(fileName, true);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), charset));
            out.write(content);
            out.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public static void writeFile(String fileName, InputStream is) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] bs = new byte[512];
        boolean n = false;

        int n1;
        while((n1 = is.read(bs)) != -1) {
            fos.write(bs, 0, n1);
        }

        is.close();
        fos.close();
    }

    public static String readFile(String fileName) {
        try {
            File e = new File(fileName);
            String charset = getCharset(e);
            StringBuffer sb = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));

            String str;
            while((str = in.readLine()) != null) {
                sb.append(str + "\r\n");
            }

            in.close();
            return sb.toString();
        } catch (IOException var6) {
            var6.printStackTrace();
            return "";
        }
    }

    public static String getCharset(File file) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];

        try {
            boolean e = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if(read == -1) {
                return charset;
            }

            if(first3Bytes[0] == -1 && first3Bytes[1] == -2) {
                charset = "UTF-16LE";
                e = true;
            } else if(first3Bytes[0] == -2 && first3Bytes[1] == -1) {
                charset = "UTF-16BE";
                e = true;
            } else if(first3Bytes[0] == -17 && first3Bytes[1] == -69 && first3Bytes[2] == -65) {
                charset = "UTF-8";
                e = true;
            }

            bis.reset();
            if(!e) {
                label72:
                do {
                    do {
                        if((read = bis.read()) == -1 || read >= 240 || 128 <= read && read <= 191) {
                            break label72;
                        }

                        if(192 <= read && read <= 223) {
                            read = bis.read();
                            continue label72;
                        }
                    } while(224 > read || read > 239);

                    read = bis.read();
                    if(128 <= read && read <= 191) {
                        read = bis.read();
                        if(128 <= read && read <= 191) {
                            charset = "UTF-8";
                        }
                    }
                    break;
                } while(128 <= read && read <= 191);
            }

            bis.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return charset;
    }

  /*  public static String readByClassPath(String filePath) {
        return readByClassPath(filePath, (ClassLoader)null);
    }*/

   /* public static String readByClassPath(String filePath, ClassLoader classLoader) {
        InputStream is = null;

        InputStreamReader isr;
        try {
            URL e = null;
            if(classLoader != null) {
                e = classLoader.getResource("/" + filePath);
            } else {
                e = FileUtil.class.getResource("/" + filePath);
            }

            is = e.openStream();
            isr = new InputStreamReader(is);
            BufferedReader e1 = new BufferedReader(isr);
            String s = null;

            String fileText;
            for(fileText = ""; (s = e1.readLine()) != null; fileText = fileText + s + "\r\n") {
                ;
            }

            String var8 = fileText;
            return var8;
        } catch (IOException var18) {
            var18.printStackTrace();
            isr = null;
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

        }

        return isr;
    }*/

    public static boolean isExistFile(String dir) {
        boolean isExist = false;
        File fileDir = new File(dir);
        if(fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            if(files != null && files.length != 0) {
                isExist = true;
            }
        }

        return isExist;
    }

    public static byte[] readByte(InputStream is) {
        try {
            byte[] e = new byte[is.available()];
            is.read(e);
            return e;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static byte[] readByte(String fileName) {
        try {
            FileInputStream e = new FileInputStream(fileName);
            byte[] r = new byte[e.available()];
            e.read(r);
            e.close();
            return r;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static boolean writeByte(String fileName, byte[] b) {
        try {
            BufferedOutputStream e = new BufferedOutputStream(new FileOutputStream(fileName));
            e.write(b);
            e.close();
            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDir(File dir) {
        if(dir.isDirectory()) {
            String[] children = dir.list();

            for(int i = 0; i < children.length; ++i) {
                boolean success = deleteDir(new File(dir, children[i]));
                if(!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    public static void serializeToFile(Object obj, String fileName) {
        try {
            ObjectOutputStream e = new ObjectOutputStream(new FileOutputStream(fileName));
            e.writeObject(obj);
            e.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public static Object deserializeFromFile(String fileName) {
        try {
            File e = new File(fileName);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(e));
            Object obj = in.readObject();
            in.close();
            return obj;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String inputStream2String(InputStream input, String charset) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input, charset));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while((line = in.readLine()) != null) {
            buffer.append(line + "\n");
        }

        return buffer.toString();
    }

    public static String inputStream2String(InputStream input) throws IOException {
        return inputStream2String(input, "utf-8");
    }

    public static File[] getFiles(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    public static void createFolderFile(String path) {
        createFolder(path, true);
    }

    public static void createFolder(String path, boolean isFile) {
        if(isFile) {
            path = path.substring(0, path.lastIndexOf(File.separator));
        }

        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

    }

    public static void renameFolder(String path, String newName) {
        File file = new File(path);
        if(file.exists()) {
            file.renameTo(new File(newName));
        }

    }

    public static ArrayList<File> getDiretoryOnly(File dir) {
        ArrayList dirs = new ArrayList();
        if(dir != null && dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isDirectory();
                }
            });

            for(int i = 0; i < files.length; ++i) {
                dirs.add(files[i]);
            }
        }

        return dirs;
    }

    public ArrayList<File> getFileOnly(File dir) {
        ArrayList dirs = new ArrayList();
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });

        for(int i = 0; i < files.length; ++i) {
            dirs.add(files[i]);
        }

        return dirs;
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        return file.delete();
    }

    public static boolean copyFile(String from, String to) {
        File fromFile = new File(from);
        File toFile = new File(to);
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(fromFile);
            fos = new FileOutputStream(toFile);
            byte[] buf = new byte[4096];

            int e;
            while((e = fis.read(buf)) != -1) {
                fos.write(buf, 0, e);
            }

            fos.flush();
            fos.close();
            fis.close();
            return true;
        } catch (IOException var8) {
            var8.printStackTrace();
            return false;
        }
    }

    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        try {
            inputChannel = (new FileInputStream(source)).getChannel();
            outputChannel = (new FileOutputStream(dest)).getChannel();
            outputChannel.transferFrom(inputChannel, 0L, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }

    }

    public static void backupFile(String filePath) {
        String backupName = filePath + ".bak";
        File file = new File(backupName);
        if(file.exists()) {
            file.delete();
        }

        copyFile(filePath, backupName);
    }

    public static String getFileExt(File file) {
        return file.isFile()?getFileExt(file.getName()):"";
    }

    public static void copyDir(String fromDir, String toDir) throws IOException {
        (new File(toDir)).mkdirs();
        File[] file = (new File(fromDir)).listFiles();

        for(int i = 0; i < file.length; ++i) {
            if(file[i].isFile()) {
                String fromFile = file[i].getAbsolutePath();
                String toFile = toDir + "/" + file[i].getName();
                copyFile(fromFile, toFile);
            }

            if(file[i].isDirectory()) {
                copyDirectiory(fromDir + "/" + file[i].getName(), toDir + "/" + file[i].getName());
            }
        }

    }

    private static void copyDirectiory(String fromDir, String toDir) throws IOException {
        (new File(toDir)).mkdirs();
        File[] file = (new File(fromDir)).listFiles();

        for(int i = 0; i < file.length; ++i) {
            if(file[i].isFile()) {
                String fromName = file[i].getAbsolutePath();
                String toFile = toDir + "/" + file[i].getName();
                copyFile(fromName, toFile);
            }

            if(file[i].isDirectory()) {
                copyDirectiory(fromDir + "/" + file[i].getName(), toDir + "/" + file[i].getName());
            }
        }

    }

    public static String getFileSize(File file) throws IOException {
        if(file.isFile()) {
            FileInputStream fis = new FileInputStream(file);
            int size = fis.available();
            fis.close();
            return getSize((double)size);
        } else {
            return "";
        }
    }

    public static String getSize(double size) {
        DecimalFormat df = new DecimalFormat("0.00");
        double ss;
        if(size > 1048576.0D) {
            ss = size / 1048576.0D;
            return df.format(ss) + " M";
        } else if(size > 1024.0D) {
            ss = size / 1024.0D;
            return df.format(ss) + " KB";
        } else {
            return size + " bytes";
        }
    }

    public static String getParentDir(String baseDir, String currentFile) {
        File f = new File(currentFile);
        String parentPath = f.getParent();
        String path = parentPath.replace(baseDir, "");
        return path.replace(File.separator, "/");
    }

    public static String readFromProperties(String fileName, String key) {
        String value = "";
        BufferedInputStream stream = null;

        try {
            stream = new BufferedInputStream(new FileInputStream(fileName));
            Properties e = new Properties();
            e.load(stream);
            value = e.getProperty(key);
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

        return value;
    }

    public static boolean saveProperties(String fileName, String key, String value) {
        StringBuffer sb = new StringBuffer();
        boolean isFound = false;
        BufferedReader in = null;

        boolean var7;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));

            String ex;
            while((ex = in.readLine()) != null) {
                if(ex.startsWith(key)) {
                    sb.append(key + "=" + value + "\r\n");
                    isFound = true;
                } else {
                    sb.append(ex + "\r\n");
                }
            }

            if(!isFound) {
                sb.append(key + "=" + value + "\r\n");
            }

            writeFile(fileName, sb.toString(), "utf-8");
            var7 = true;
            return var7;
        } catch (Exception var17) {
            var17.printStackTrace();
            var7 = false;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

        }

        return var7;
    }

    public static boolean delProperties(String fileName, String key) {
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;

        boolean var5;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));

            String ex;
            while((ex = in.readLine()) != null) {
                if(!ex.startsWith(key)) {
                    sb.append(ex + "\r\n");
                }
            }

            writeFile(fileName, sb.toString(), "utf-8");
            var5 = true;
            return var5;
        } catch (Exception var15) {
            var15.printStackTrace();
            var5 = false;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

        }

        return var5;
    }

    public static String getClassesPath() {
        String path = FileUtil.class.getClassLoader().getResource("").getPath();
        if("\\".equals(File.separator)) {
            path = StringUtil.trimPrefix(path, "/");
        }

        path = path.replace("\\", "/");
        path = StringUtil.trimSuffix(path, "/");
        return path;
    }

    public static String getWebRootPath() {
        String path = getClassesPath();
        path = path.substring(0, path.lastIndexOf("WEB-INF"));
        path = StringUtil.trimSuffix(path, "/");
        return path;
    }

    public static void writeInput(InputStream is, OutputStream out) throws IOException {
        try {
            byte[] ex = new byte[1024];
            boolean n = false;

            int n1;
            while((n1 = is.read(ex)) != -1) {
                out.write(ex, 0, n1);
            }

            out.flush();
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            if(is != null) {
                is.close();
            }

            if(out != null) {
                out.close();
            }

        }

    }

    public static String clobToString(Clob clob) {
        String reString = "";

        try {
            Reader ex = clob.getCharacterStream();
            BufferedReader br = new BufferedReader(ex);
            String s = br.readLine();

            StringBuffer sb;
            for(sb = new StringBuffer(); s != null; s = br.readLine()) {
                sb.append(s);
            }

            reString = sb.toString();
            return reString;
        } catch (Exception var6) {
            return reString;
        }
    }

    public static void downLoad(File file, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream buff = null;
        ServletOutputStream out = null;

        try {
            buff = new BufferedInputStream(fis);
            out = response.getOutputStream();
            IOUtils.copy(buff, out);
        } finally {
            out.flush();
            fis.close();
            buff.close();
            out.close();
        }

    }

    public static void downLoad(InputStream is, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        inputToOut(is, response.getOutputStream());
    }

    public static void inputToOut(InputStream is, OutputStream out) throws UnsupportedEncodingException, IOException {
        BufferedInputStream buff = null;

        try {
            buff = new BufferedInputStream(is);
            IOUtils.copy(buff, out);
        } finally {
            out.flush();
            is.close();
            buff.close();
            out.close();
        }

    }

    public static int getImageWidth(String path) throws FileNotFoundException, IOException {
        File picture = new File(path);
        if(picture.exists()) {
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
            return sourceImg.getWidth();
        } else {
            return 0;
        }
    }

    public static int getImageHeight(String path) throws FileNotFoundException, IOException {
        File picture = new File(path);
        if(picture.exists()) {
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
            return sourceImg.getHeight();
        } else {
            return 0;
        }
    }






}
