package com.zhenjinzi.util;import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
public class FileUtil {
	 /**
     * 将字节流转换成字符串返回 
     * @param is
     *            输入流
     * @return 字符串
     */
    public static String readFileByLines(InputStream is) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将文件一行一行的读成List返回
     * 
     * @param file
     *            需要读取的文件
     * @return 文件的一行就是一个List的Item的返回
     */
    public static List<String> readFileToList(File file) {
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }

    /**
     * 将文件按照一定的编码方式一行一行的读成List返回
     * 
     * @param file
     *            需要读取的文件
     * @param encodType
     *            字符编码
     * @return 文件的一行就是一个List的Item的返回
     */
    public static List<String> readFileToList(File file, String encodType) {
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), encodType));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if (!(tempString.charAt(0) >= 'a' && tempString.charAt(0) <= 'z'))
                    tempString = tempString.substring(1);
                list.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }

    /**
     * 将指定的字符串内容以指定的方式写入到指定的文件中
     * 
     * @param file
     *            需要写人的文件
     * @param content
     *            需要写入的内容
     * @param flag
     *            是否追加写入
     */
    public static void writeFile(File file, String content, Boolean flag) {
        try {
            if (!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file, flag);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定的字符串内容以指定的方式及编码写入到指定的文件中
     * 
     * @param file
     *            需要写人的文件
     * @param content
     *            需要写入的内容
     * @param flag
     *            是否追加写入
     * @param encodType
     *            文件编码
     */
    public static void writeFile(File file, String content, Boolean flag,
            String encodType) {
        try {
            FileOutputStream writerStream = new FileOutputStream(file, flag);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    writerStream, encodType));
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝文件夹
     * 
     * @param oldPath
     *            源目录
     * @param newPath
     *            目标目录
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件重命名
     * 
     * @param oldName
     *            源文件名
     * @param newName
     *            新文件名
     */
    public static void reName(String oldName, String newName) {
        File oldF = new File(oldName);
        File newF = new File(newName);
        oldF.renameTo(newF);
    }

    /**
     * 将一个文件列表文件中所有文件拷贝到指定目录中
     * 
     * @param listFile
     *            包含需要拷贝的文件的列表的文件，每个文件写在一行
     * @param targetFloder
     *            目标目录
     */
    public static void copyFilesFromList(String listFile, String targetFloder) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(listFile));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                copyFile(tempString, targetFloder);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 拷贝文件
     * 
     * @param oldPath
     *            源文件
     * @param newPath
     *            目标文件
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            File temp = new File(oldPath);
            FileInputStream input = new FileInputStream(temp);
            FileOutputStream output = new FileOutputStream(newPath + "/"
                    + (temp.getName()).toString());
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();

            output.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件列表
     * 
     * @param files
     *            需要删除的文件/文件夹列表
     * @return 删除成功true，否则返回false
     */
    public static boolean deleteFiles(List<String> files) {
        boolean flag = true;
        for (String file : files) {
            flag = delete(file);
            if (!flag)
                break;
        }
        return flag;
    }

    /**
     * 删除文件或文件夹
     * 
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除文件
     * 
     * @param fileName
     *            要删除的文件的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile())
            return file.delete();
        return false;
    }
    
    public static String loadFileContent(String txtFilename){
    	   File file = new File(txtFilename);
           if (file.exists() && file.isFile()){
        	   String content = "";
               BufferedReader reader = null;
               try {
                   System.out.println("以行为单位读取文件内容，一次读一整行：");
                   reader = new BufferedReader(new FileReader(file));
                   String tempString = null;
                   while ((tempString = reader.readLine()) != null) {
                      content += tempString;
                   }
                   reader.close();
                   return content;
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           return "";
    }

    /**
     * 删除目录及目录下的文件
     * 
     * @param dir
     *            要删除的目录路径
     * @return 删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        if ((!dirFile.exists()) || (!dirFile.isDirectory()))
            return false;
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        return dirFile.delete();
    }
    
    public static void rewrite(File file, String data) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {       
            if(bw != null) {
               try {
                   bw.close();
               } catch(IOException e) {
                   e.printStackTrace();
               }
            }           
        }
    }
   
    public static List<String> readList(File file) {
        BufferedReader br = null;
        List<String> data = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));
            for(String str = null; (str = br.readLine()) != null; ) {
                data.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
               try {
                   br.close();
               } catch(IOException e) {
                   e.printStackTrace();
               }
            }
        }
        return data;
    }
}
