package pack;
import java.io.*;
import java.util.LinkedList;

public class Main {
	
	static boolean error=false;
	static int i=0;
	static int fila=0;
	static char aux;
	static String pw;
	static String fin="$";

	static LinkedList<String> cadena = new LinkedList<String>();
	static String vt[]=	{"<(>","<)>","<{>","<}>","<+>","<*>","<->","<O>","<Y>","<NO>","<Id>","<Const>",
						"<VERDADERO>","<FALSO>","<Para>","<desde>","<hasta>","<Si>","<entonces>","<=>",
						"<==>","<<>","<>>","<>=>","<<=>","<;>","<entero>","<real>","<logica>","<return>","$"};
	static String Matriz[][] = new String [26][10];
	
	
	//Matriz de VN y VT, con Producciones.
	private static void cargarMatriz()
	{
		Matriz[0][0]="P"; 	Matriz[0][1]="DV DF LC"; 
							Matriz[0][2]="DV DF"; 
							Matriz[0][3]="DF LC";
							Matriz[0][4]="DV LC"; 
							Matriz[0][5]="DV"; 
							Matriz[0][6]="LC"; 
							Matriz[0][7]="DF";
							Matriz[0][8]="#";
		Matriz[1][0]="DV"; 	Matriz[1][1]="Tipo <Id> <;> DV"; 
							Matriz[1][2]="Tipo <Id> <;>";
							Matriz[1][3]="#";
		Matriz[2][0]="DF"; 	Matriz[2][1]="Tipo <Id> LP <{> LC <;> <return> <Id> <;> <}> DF";
							Matriz[2][2]="Tipo <Id> LP <{> <;> <return> <Id> <;> <}> DF";
							Matriz[2][3]="Tipo <Id> LP <{> LC <;> <return> <Id> <;> <}>";
							Matriz[2][4]="Tipo <Id> LP <{> <;> <return> <Id> <;> <}>"; 
							Matriz[2][5]="#";
		Matriz[3][0]="LC";	Matriz[3][1]="CAsignacion LC";
							Matriz[3][2]="CPara LC";
							Matriz[3][3]="CSiEntonces LC";
							Matriz[3][4]="CLLamadoFuncion LC";
							Matriz[3][5]="CAsignacion";
							Matriz[3][6]="CPara";
							Matriz[3][7]="CSiEntonces";
							Matriz[3][8]="CLLamadoFuncion";
							Matriz[3][9]="#";
		Matriz[4][0]="Tipo";Matriz[4][1]="<entero>";
							Matriz[4][2]="<real>";
							Matriz[4][3]="<logica>";
							Matriz[4][4]="#";
		Matriz[5][0]="LP";	Matriz[5][1]="<(>LPCont<)>";
							Matriz[5][2]="<(><)>";
							Matriz[5][3]="#";
		Matriz[6][0]="LPCont";
							Matriz[6][1]="Tipo <Id><;> LPCont";
							Matriz[6][2]="Tipo <Id>";
							Matriz[6][3]="#";
		Matriz[7][0]="CAsignacion";
							Matriz[7][1]="<Id><=>ExpEntera<;>";
							Matriz[7][2]="#";
		Matriz[8][0]="CPara";
							Matriz[8][1]="<Para> <Id> <desde> ExpEntera <hasta> ExpEntera <{>LC<}>";
							Matriz[8][2]="<Para> <Id> <desde> ExpEntera <hasta> ExpEntera <{><}>";
							Matriz[8][3]="#";
		Matriz[9][0]="CSiEntonces";
							Matriz[9][1]="<Si> ExpLogica <entonces> <{>LC<}>";
							Matriz[9][2]="<Si> ExpLogica <entonces> <{><}>";
							Matriz[9][3]="#";
		Matriz[10][0]="CLLamadoFuncion";
							Matriz[10][1]="<Id> LLPar <;>";
							Matriz[10][2]="#";
		Matriz[11][0]="LLPar";
							Matriz[11][1]="<(>LLParCont<)>";
							Matriz[11][2]="<(><)>";
							Matriz[11][3]="#";
		Matriz[12][0]="LLParCont";
							Matriz[12][1]="<Id><;>LLParCont";
							Matriz[12][2]="<Id>";
							Matriz[12][3]="#";
		Matriz[13][0]="ExpEntera";
							Matriz[13][1]="Termino ExpA";
							Matriz[13][2]="Termino";
							Matriz[13][3]="#";
		Matriz[23][0]="ExpA";
							Matriz[23][1]="<+>Termino ExpA";
							Matriz[23][2]="<->Termino ExpA";
							Matriz[23][3]="<+>Termino";
							Matriz[23][4]="<->Termino";
							Matriz[23][5]="#";
		Matriz[14][0]="Termino";
							Matriz[14][1]="Factor TerminA";
							Matriz[14][2]="Factor";
							Matriz[14][3]="#";
		Matriz[22][0]="TerminA"; 
							Matriz[22][1]="<*>Factor TerminA";
							Matriz[22][2]="<*>Factor";
							Matriz[22][3]="#";
		Matriz[15][0]="Factor";
							Matriz[15][1]="<(>ExpEntera<)>";
							Matriz[15][2]="<Const>";
							Matriz[15][3]="<Id>";
							Matriz[15][4]="#";
		Matriz[16][0]="ExpLogica";
							Matriz[16][1]="OperandoLogico ExpLog1";
							Matriz[16][2]="OperandoLogico";
							Matriz[16][3]="#";
		Matriz[24][0]="ExpLog1";
							Matriz[24][1]="<O> OperandoLogico ExpLog1";
							Matriz[24][2]="<O> OperandoLogico";
							Matriz[24][3]="#";
		Matriz[17][0]="OperandoLogico";
							Matriz[17][1]="FactorLogico OperandoLog1";
							Matriz[17][2]="<NO> FactorLogico OperandoLog1";
							Matriz[17][3]="FactorLogico";
							Matriz[17][4]="#";
		Matriz[25][0]="OperandoLog1";
							Matriz[25][1]="<Y> FactorLogico OperandoLog1";
							Matriz[25][2]="<Y> FactorLogico";
							Matriz[25][3]="#";
		Matriz[18][0]="FacorLogico";
							Matriz[18][1]="<(>ExpLogica<)>";
							Matriz[18][2]="ConstLogica";
							Matriz[18][3]="<Id>";
							Matriz[18][4]="Comparacion";
							Matriz[18][5]="#";
		Matriz[19][0]="ConstLogica";
							Matriz[19][1]="<VERDADERO>";
							Matriz[19][2]="<FALSO>";
							Matriz[19][3]="#";
		Matriz[20][0]="Comparacion";
							Matriz[20][1]="ExpEntera Operador ExpEntera";
							Matriz[20][2]="#";
		Matriz[21][0]="Operador";
							Matriz[21][1]="<==>";
							Matriz[21][2]="<>>";
							Matriz[21][3]="<<>";
							Matriz[21][4]="<>=>";
							Matriz[21][5]="<<=>";
							Matriz[21][6]="#";
	}
	
	
	private static LinkedList<String> listaTokens(String produccion)
	{
		String e="";
		String e1="";
		char aux;
		LinkedList<String> lista = new LinkedList<String>();
		
		for (int i=0;i<produccion.length();i++)
		{
			aux=produccion.charAt(i);
			if (aux == 32)
			{
				continue;
			}
			e1=String.valueOf(aux);
			e=e.concat(e1);
			if (esTerminal(e))
			{
				lista.add(e);
				e="";
				continue;
			}
			if (esNoTerminal(e))
			{
				lista.add(e);
				e="";
			}
		}
		return lista;
	}
	
	
	//Averigua si es VT
	private static boolean esTerminal(String terminal)
	{
		boolean marca=false;
		for (int nvt=0;nvt<vt.length;nvt++)
		{
			if (vt[nvt].equals(terminal))
			{
				marca = true;
				break;
			}
		}
		return marca;		
	}
	
	//Averigua si es VN
	private static boolean esNoTerminal(String noterminal)
	{
		boolean marca=false;
		for (int i=0;i<26;i++)
		{			
			if (Matriz[i][0].equals(noterminal))
			{
				marca=true;
				break;
			}
		}
		return marca;
	}
	
	//Indica la cantidad de producciones de un VN para utilizar en el procedimiento PN.
	private static int cantProducciones(String noterminal)
	{
		int contador=1;
		for (int i=0;i<Matriz.length;i++)
		{
			if (Matriz[i][0].equals(noterminal))
			{
				while (!Matriz[i][contador].equals("#"))
				{
					contador=contador+1;
				}
				contador=contador-1;
				break;
			}
		}
		return contador;
	}
	
	//Devuelve la posicion de un VN en la Matriz, esto luego se utiliza para el procedimineto cantProducciones(VN).
	private static int posicion(String noterminal)
	{
		int posicion=0;
		for (int i=0;i<Matriz.length;i++)
		{
			if (Matriz[i][0].equals(noterminal))
			{
				posicion = i;
				break;
			}
		}
		return posicion;
	}
	
	//Procedimiento para cada VN.
	private static String PN(String noterminal,String punteroaW)
	{
		int j=1;
		String pwaux1;
		
		pwaux1=punteroaW;
		error=false;
		LinkedList<String> prodVN = new LinkedList<String>();
		
		while (!error && j<=cantProducciones(noterminal))
			{
				System.out.println("PN"+j+" "+noterminal+" puntero cadena: "+punteroaW+" "+i);
				error=true;
				prodVN = listaTokens (Matriz[fila][j]);
				punteroaW = Procesar(prodVN/*Matriz[fila][j]*/,punteroaW);
				j=j+1;
			}
		if (!error)
		{
			punteroaW=pwaux1;
			return punteroaW;
		}
		else
		{
			return punteroaW;
		}
		
	}
	
	//Procedimiento para cada Produccion, invocado desde PN.
	private static String Procesar(LinkedList<String> produccion, String punteroaW1)
	{		
		//char auxProcesar;
		String xj;
		String pwaux;
		int iaux;
		int auxfila=0;
		
		auxfila=fila;
		pwaux=punteroaW1;
		iaux=i;
		
		for (int k=0;k<produccion.size();k++)
		{
			xj = produccion.get(k);
			
			System.out.println("Procesar: "+punteroaW1+" "+xj);
			
			if (esTerminal(xj))
					{
						if (punteroaW1.equals(xj))
						{
							i=i+1;
							punteroaW1 = cadena.get(i);
							System.out.println("Sig puntero de cadena: "+punteroaW1+" "+i);
						}
						else 
						{
							punteroaW1=pwaux;
							i=iaux;						
							error=false;
							return punteroaW1;
						}
					}
			else
			{
				if (esNoTerminal(xj))
				{
					fila = posicion(xj);
					punteroaW1 = PN(Matriz[fila][0],punteroaW1);
					if (!error)
					{
						fila=auxfila;
						punteroaW1=pwaux;
						i=iaux;
						return punteroaW1;
					}
				}
			}
		}
		return punteroaW1;
	}
	
	
	private static BufferedReader buffer;
	private static BufferedReader buffer1;
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	// Declaracion de todo lo reservado
	String palabrasreservadas[]={"O","Y","NO","VERDADERO","FALSO","Para","desde","hasta","Si",
								"entonces","entero","real","logica","return"}; //0-13
	String nro[]={"0","1","2","3","4","5","6","7","8","9"};//0-9
	String simbolosreservados[]={"(",")","{","}","+","*","-","=","==","<",">","<=",">=",";"};//0-13								
	
	//Para poder leer y escribir sobre un txt
	FileReader r = new FileReader("code.txt");
	buffer = new BufferedReader(r);
	
	FileReader r1 = new FileReader("tokens.txt");
	buffer1 = new BufferedReader(r1);
	
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
	String tokens="";
	
	
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
	wr.write(fin);
	wr.close();
	bw.close();
	
	cargarMatriz();
	
	tokens=buffer1.readLine();
	
	cadena=listaTokens(tokens);
	
	
	error=false;
	i=0; 
	pw=cadena.get(i);
	
	pw = PN(Matriz[fila][0],pw);
	
	if (error && pw.equals(fin))
	{
		System.out.println("pertenece");
	}
	else
	{
		System.out.println("no pertenece");
	}
	
	}
	}
