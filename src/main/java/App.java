/*
coevyat3@gmail.com
 */
import coinns.Coin;
import coinsType.CoinFactory;
import coinsType.Coins;
import results.Result;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class App {
    private static Scanner sc = new Scanner(System.in);
    private static List<Double> list = new ArrayList<>();             // result list
    private static List<Result> resultArrayList = new ArrayList<>(); // Result objects
    private static boolean outerLoop = true;
    private static boolean innerLoop = false;
    private static final String  file = "Result.txt";
    private static final String file2 = "Result2.txt";
    private static List<Coin> cn = new ArrayList<>();           //list of instance of Coin
    static Character input;
    static DecimalFormat f = new DecimalFormat("##.###");


    public static void main(String[] args) throws IOException,ClassNotFoundException {

        hello();
        run();
        goodbye();
        display();
        readingValuesFile();
        System.out.println(readObjList());
        cn.clear();
        list.clear();
        resultArrayList.clear();

    }
    private static void hello() {
        System.out.println("Welcome to currency convertor");
    }

    /**
     * @throws IOException
     */
    public static void run() throws IOException {

        while (outerLoop) {
            System.out.println("Please Choose an option (1/2/3)\n1.Dollars to Shekels\n2.Shekels To Dollars\n3.Euro To Shekels"); //Display menu
            input=sc.next().charAt(0);//Takes only the first char if user enter more Characters.
            switch (input) {
                case '1', '2', '3' -> createObject();
                default -> System.out.print("Please Enter a valid value\n");
            } //end switch case


            while (innerLoop) {
                System.out.println("Start over (Y/N)");
                Character str = sc.next().charAt(0); //Takes only the first char if user enter more Characters.
                if (str.equals('n') || str.equals('N')) {
                    innerLoop = false;
                    writingValuesFile();
                    WriteObjList();



                } else if (str.equals('y') || str.equals('Y')) {
                    innerLoop = false;
                    outerLoop = true;


                } else {
                    System.out.print("Please Enter a valid answer ");
                    innerLoop = true;

                }

            } //innerLoop
        } //outerLoop

    }

    /**
     * This method will create an Coins instance only if amount>0
     * else its will hold the Option(Coin instance user want to convert),  that already input and will ask again for a valid amount to convert
     * Option 1 - Dollar to Shekel
     * Option 2- Shekel to Dollar.
     * Option 3- Euro to shekel
     * ADD to list(double), list(Result) & list(Coin)
     * Print the current result
     */
    public static void createObject(){
       Result res;   String str; double result;
        System.out.println("Please Enter an amount to convert");
        double value = sc.nextDouble();
        if(input.equals('1') && value>0.0d){
            Coin coin = CoinFactory.getCoinInstance(Coins.USD);         //Coin instance
            result = Objects.requireNonNull(coin).calculate(value);    //calculate amount ->Making sure Instance not null
            str="Dollar to Shekels";                                  //String str to add to a Result obj
            res =new Result(result, str);                            //New Result object
            resultArrayList.add(res);                               //adding a Result object list(Result)
            list.add(Double.valueOf(f.format(result)));            //adding result to list(double)
            cn.add(coin);                                         //Adding Coin into list
            System.out.println(f.format(result));                //Print result value

        }
        else if(input.equals('2') && value>0.0d){
            Coin  coin = CoinFactory.getCoinInstance(Coins.ILS);
            result = Objects.requireNonNull(coin).calculate(value);
            str="Shekels to Dollar";
            res =new Result (result, str);
            resultArrayList.add(res);
            list.add(Double.valueOf(f.format(result)));
            cn.add(coin);
            System.out.println(f.format(result));


        }
        else if(input.equals('3') && value>0.0d){
            Coin coin = CoinFactory.getCoinInstance(Coins.EURO);
            result = Objects.requireNonNull(coin).calculate(value);
            str="Euro to Shekel";
            res =new Result (result, str);
            resultArrayList.add(res);
            list.add(Double.valueOf(f.format(result)));
            cn.add(coin);
            System.out.println(f.format(result));

        }
        else{
            System.out.println("Not valid, Enter a valid amount");
            innerLoop=false;
            createObject(); // Back again with the same option ( coin type ) user entered


        }
        outerLoop=false;
        innerLoop=true;
    }


    /**
     * Print the list(double)
     */
    public static void display() {
        System.out.println(list.toString());

    }

    /**
     * @throws IOException
     * Writing List double values into file text
     */
    public static void writingValuesFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Double line : list) {
            bw.write(line + "\n");
            bw.newLine();
        }
        bw.close();


    }

    /**
     * @throws IOException
     */
    public static void readingValuesFile() throws IOException {

        System.out.println("\tRead from file: ");
        System.out.println("---------------------------------");
        Scanner s = new Scanner(new BufferedReader(new FileReader(file)));

        while (s.hasNext()) {
            System.out.println(s.next());

        }
        System.out.println("---------------------------------");
        s.close();
    }

    public static void goodbye() {
        System.out.println("\nThanks for using our  currency convertor\n");
    }

    /**
     * @throws IOException
     * writing list of Coin instance
     */
    public static void WriteObjList() throws IOException {

        FileOutputStream writeData = new FileOutputStream(file2);
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(cn);
        writeData.close();
        writeStream.close();
    }


    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ArrayList<Coin> readObjList() throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream(file2);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        ArrayList c = (ArrayList<Coin>) readStream.readObject();
        readStream.close();
        readData.close();
        return c;


    }


}



