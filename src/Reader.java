import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;


public class Reader {
	
	public static void main(String[] args) {
		
		IOException ex = null;
		String line = "";
        String cvsSplitBy = ",";
        String data[];

		Scanner s = new Scanner(System.in);
		
		do {
		
		System.out.println("Введіть iм'я (+шлях) до файлу (для завершення - 'exit'): ");
		String file = s.nextLine();
		if (file.contains("exit"))
			break;
		
		try  {
			BufferedReader br = new BufferedReader(new FileReader(file));
			if(file.endsWith(".csv")) {
				
				String newfile = "New"+file.substring(0, file.length()-4)+".txt";
				String resultFile ="";
				int numline = 1;
				
				System.out.println("Зчитуємо CSV файл:");
				
				while ((line = br.readLine()) != null) {

					int len = StrLength(line);
					System.out.println("Рядок №" + numline + ", кількість символів :" + len);
					
					data = line.split(cvsSplitBy);

					for(int i = 0; i<data.length; i++) {
						
						System.out.print(data[i]+"\t");
						resultFile+=data[i] + "\t";
					}
					
					resultFile+="\n";
					System.out.println();
					numline++;
				}
				
				
				PrintWriter writer = new PrintWriter(new FileOutputStream(newfile));
				writer.write(resultFile);
				writer.close();
				
				System.out.println("\nЗчитані дані збережено в файл "+newfile);
				
			}else if(file.endsWith(".xml")) {
				
				String newfile = "New"+file.substring(0, file.length()-4)+".txt";
				int numline = 1;
				String resultFile = "";
				
				System.out.println("Зчитуємо XML файл:");
				
				while ((line = br.readLine()) != null) {
					
					int len = StrLength(line);
					System.out.println("Рядок №" + numline + ", кількість символів :" + len);
					
					if(numline == 1)
						System.out.println(line.substring(3, line.length()-1));
					else
						System.out.println(line);
					
					resultFile+=line+ "\n";
					numline++;
				}
				
				PrintWriter writer = new PrintWriter(new FileOutputStream(newfile));
				writer.write(resultFile);
				writer.close();
				
				System.out.println("\nЗчитані дані збережено в файл "+newfile);
			
			} else
				System.out.println("Невірний формат файлу");
			System.exit(0);

			br.close();
		}  catch (IOException e) {
			System.out.println(e.getMessage());
			ex =  e;
        }	
		}while(ex!=null);
		s.close();
	}
	
	public static int StrLength(String s) {
		String line = s.replaceAll(",", "");;
		return line.length();
	}
}

