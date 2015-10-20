package pack;
import java.io.*;

public class Main {

	
	private static BufferedReader buffer;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	// Declaracion de todo lo reservado
	String palabrasreservadas[]={"O","Y","NO","VERDADERO","FALSO","para","desde","hasta","Si",
			"entonces","entero","real","logica","return"}; //0-13
	String nro[]={"0","1","2","3","4","5","6","7","8","9"};//0-9
	String simbolosreservados[]={"(",")","{","}","+","*","-","=","==","<",">","<=",">=",";"};//0-13								
	
	//Para poder leer y escribir sobre un txt
	FileReader r = new FileReader("code.txt");
	buffer = new BufferedReader(r);
	
	FileWriter w=new FileWriter ("tokens.txt");
	BufferedWriter bw=new BufferedWriter(w);
	PrintWriter wr=new PrintWriter(bw);	
	
	//Declaracion de variables
	char vble;
	char aux;
	char aux1;
	boolean check=false;
	boolean sig=false;
	boolean pal=false;
	boolean id=false;
	int ii;
	String a="";
	String b="";
	String b1="";
	String texto="";
	
	//DA PROGRAM (TP1)
	while (texto!=null)
	{
		texto=buffer.readLine();
		if (texto==null)
			break;
	
	for (int i=0; i<texto.length();i++)
	{
		vble = texto.charAt(i);	
		if (vble == 32)
		{
			continue;
		}
		aux=vble;
		a=String.valueOf(aux);
		ii=i+1;
		check=false;
		sig=false;
		pal=false;
		id=true;
		b="";
		for (int y=0; y<simbolosreservados.length; y++)
		{
			if (simbolosreservados[y].equals(a))
			{
				if ((i+1)!=texto.length())
				{
					aux1=texto.charAt(ii);
					b=String.valueOf(aux1);
					b1=a.concat(b);
					for (int y1=0; y1<simbolosreservados.length;y1++)
					{
						if (simbolosreservados[y1].equals(b1))
						{
							i=i+1;
							wr.write("<"+b1+">");
							check=true;
							break;
						}
					}
				}
				if (check==false)
				{
					wr.write("<"+a+">");
					check=true;
					break;
				}
			}
		}
		if (!check)
		{
			for (int x1=0; x1<nro.length; x1++)
			{
				if (nro[x1].equals(a))
				{
					sig=true;
					i=i+1;
					x1=0;
					aux=texto.charAt(i);
					a=String.valueOf(aux);
				}
			}
			if (sig)
			{
				check=true;
				i=i-1;
				wr.write("<Const>");
			}
		}
		if (!check)
		{
			for (int z=97; z<=122; z++)
			{
				if ((vble == z) || (vble == (z-32)))
				{					
					b=b.concat(a);
					i=i+1;
					vble=texto.charAt(i);
					a=String.valueOf(vble);
					pal=true;
					z=96;
				}
			}
			if (pal)
			{
				i=i-1;
				vble=texto.charAt(i);
				for (int z1=0; z1<palabrasreservadas.length; z1++)
				{
					if (palabrasreservadas[z1].equals(b))
					{
						wr.write("<"+b+">");
						id=false;
						b="";
						break;
					}
				}
				if (id)
				{
					b="";
					wr.write("<Id>");
				}
			}
		}
	}
	}
	wr.close();
	bw.close();
	}
	}
