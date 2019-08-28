package com.batiaev.java3.lesson3;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lesson3_Batiaev
 *
 * @author anton
 * @since 26/08/19
 */
public class Lesson3_Batiaev implements Serializable {
    public static void main(String[] args) throws IOException {

        File file = new File("./test");

//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getCanonicalPath());
//        System.out.println(file.getPath());

       //file();
        //example1();
        //example2();
       //byteArrayStream();
        //fileStream();
        pipedStream();
//        sequenceStream();
       //dataStream();
        //bufferedStream();
//        objectStream();
        //bufferedWriterAndReaderArrayList();
    }

    private static void   bufferedWriterAndReaderArrayList(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Один");
        arr.add("Два");
        arr.add("Три");
        arr.add("Четыре");
        arr.add("Пять");
        System.out.println("arr.size() = " + arr.size());

        //блок записи  в файл
        File buf = new File("bufWriter.txt");
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(buf))){
            for (String s: arr){
                bw.write(s);
                bw.write(System.getProperty("line.separator"));
            }
            bw.flush();
            System.out.println(" buf.length()" + buf.length());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //блок чтения из файла
        try( BufferedReader br = new BufferedReader(new FileReader(buf))) {
            ArrayList<String> input = new ArrayList<>();
            String str;
            while ((str = br.readLine())!=null){
                input.add(str);
            }
            for (String s: input){
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void file() {
        //File testFile = new File("C:\\Users\\Андрей\\1.txt"); //работает -  в каталог C:\Users\Андрей
        //File testFile = new File("D:\\1.txt"); //работает - в каталог D:\
        File testFile = new File("1.txt"); //работает - в каталог D:\Java_Programs\GeekBrains\Java3\course-java3-stream4

        Path path = testFile.toPath();
        System.out.println(path);
        System.out.println(testFile);
        System.out.println(testFile.getAbsolutePath());
        try {
            FileInputStream source = new FileInputStream(testFile);
            Scanner scanner = new Scanner(source);
            String line = scanner.nextLine();
            System.out.println("Line contain: " + line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //example 1
    private static void example1() {
        //создаём каталог на диске D по адресу D:\etc
        File file = new File("/etc");
        //создаём каталог etc по адресу D:\Java_Programs\GeekBrains\Java3\course-java3-stream4\etc
        //File file = new File("etc");
        //создаём каталог и подкаталог на диске D по адресу D:\etc
        //File file = new File("/etc/cat1");
        file.mkdir();
        System.out.format("%s, is directory: %s\n",
                file.getAbsoluteFile(), file.isDirectory());

        String[] list = file.list(); //вывести список содержимого
        for (String s : list) {
            System.out.println(s);
        }
    }

    //example 2
    //не понял этот пример
    private static void example2() {
        File test = new File("~/./././test.txt");
        System.out.println(test.getAbsolutePath());
        try {
            System.out.println(test.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void objectStream() {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            Integer integerSave = new Integer(155);

            oos.writeObject(integerSave);
            byte[] arr = os.toByteArray();
            os.close();
            oos.close();
            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(is);
            Integer integerRead = (Integer) ois.readObject();
            ois.readLong();
            is.close();
            ois.close();
            System.out.println("Writed: " + integerSave + ", Readed: " + integerRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dataStream() {
        try {
            DataOutputStream out = new DataOutputStream(
                    new FileOutputStream("file.txt"));
            out.writeUTF("Hello world");
            out.writeUTF("Hello world");
            out.writeInt(128);
            out.writeLong(128);
//            out.write
            out.close();
            DataInputStream in = new DataInputStream(
                    new FileInputStream("file.txt"));
            System.out.println(in.readUTF());
            System.out.println(in.readUTF());
            System.out.println(in.readInt());
            System.out.println(in.readLong());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void byteArrayStream() {
        String name = "Anton";
        byte[] bytes = name.getBytes();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            stream.write(bytes);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        byte[] arr = {100, 25, 50};
        //ByteArrayInputStream in = new ByteArrayInputStream(bytes); //Ваводит А н т о н при  System.out.print((char) x + " ");
        ByteArrayInputStream in = new ByteArrayInputStream(arr); //выводит 100 25 50  при System.out.print(x + " ");
        process(in);
        int x;
        while ((x = in.read()) != -1) {
            System.out.print(x + " ");
        }
    }

    private static void process(final InputStream stream) {
//        stream.read();
    }

    private static void fileStream() {
        byte[] bw = {10, 20, 30,40,50};
        byte[] br = new byte[20];

        FileInputStream in = null;
//        try (FileOutputStream out = new FileOutputStream("12345.txt");
//             FileInputStream into = new FileInputStream("12345.txt")) {
//
//            int read = into.read();
//            System.out.println("read = " + read);
//            out.write(bw);
//            read = into.read();
//            System.out.println("read = " + read);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            in = new FileInputStream("12345.txt");
            in.skip(5);
            int count = in.read(br);
            System.out.println("Прочитано " + count + " байт");
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void pipedStream() {
        PipedInputStream in = null;
        PipedOutputStream out = null;
        try {
            in = new PipedInputStream();
            out = new PipedOutputStream();
            out.connect(in);
            out.flush();
            for (int i = 0; i < 100; i++) {
                out.write(i);
            }
            int x;
            while ((x = in.read()) != -1) {
                System.out.print(x + " ");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sequenceStream() {
        FileInputStream in1 = null, in2 = null;
        SequenceInputStream seq = null;
        FileOutputStream out = null;
        try {
            in1 = new FileInputStream("1.txt");
            in2 = new FileInputStream("2.txt");
            seq = new SequenceInputStream(in1, in2);
            seq.read();

            try (DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(
                            seq))) {
                float readFloat = dataInputStream.readFloat();
            } catch (Exception e) {

            }


            out = new FileOutputStream("3.txt");
            int rb = seq.read();
            while (rb != -1) {
                out.write(rb);
                rb = seq.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                seq.close();
            } catch (IOException e) {
            }
            try {
                out.close();
            } catch (IOException e) {
            }
        }
    }

    private static void bufferedStream() {
        try {
            File f = new File("file.txt");
            OutputStream out = new BufferedOutputStream(
                    new FileOutputStream("file.txt"));

            for (int i = 33; i <100; i++) {
                out.write(i);
                //длина файла = 0, пока пишется не в файл а в буфер который 8192 байт по умолчанию
                //System.out.println(" длина файла = " + f.length());
            }
            //без следующей строки буфер не будет передан в выходной поток и длина файла = 0 пока не out.close();
            out.flush();
            System.out.println(" длина файла = " + f.length());
            out.close();

            InputStream in = new BufferedInputStream(
                    new FileInputStream("file.txt"));
            int n;
            while ((n = in.read())!= -1) {
                System.out.print((char) n + " ");
            }
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}