import java.util.Scanner;

/**
 * Transforma una fecha del calendario Gregoriano o Juliano a su equivalente
 * usando el calendario azteca.
 * @version Octubre 2012
 * @author Jesús Vila
 */
public class CalendarioAzteca {
	public static void main(String [] args) {
	    int dia, anio, mes1;
	    Scanner in = new Scanner(System.in);
		String mes2;

		final String [] MESES = {
		        "Enero", "Febrero", "Marzo", "Abril",
                "Mayo", "Junio", "Julio", "Agosto",
                "Septiembre", "Octubre", "Noviembre", "Diciembre"
		};

		final String [] SIGNOS = {
		        "0", "Cipactli - Cocodrilo", "Ehecatl - Viento", "Calli - Casa", "Cuetzpalin - Lagartija",
                "Cóatl - Serpiente", "Miquiztli - Muerte", "Mazatl - Venado", "Tochtli - Conejo",
                "Atl - Agua", "Itzcuintli - Perro", "Ozomatli - Mono", "Malinalli - Hierba",
                "Ácatl - Caña", "Ocelotl - Jaguar", "Cuauhtli - Aguila", 	"Cozcaquauhtli - Zopilote",
                "Ollin - Movimiento", "Tecpátl - Pedernal", 	"Quiahuitl - Lluvia", "Xochitl - Flor"
		};

		do {
			System.out.print("Dia: ");
			dia = in.nextInt();

			if (dia > 31 || dia <= 0)
				System.out.println("Día invalido");
		}while(dia > 31 || dia <= 0);

		boolean mesValido;
		do {
			mes1 = -1;
			mesValido = true;
			System.out.print("Mes: ");
			mes2 = in.next();
			for (int i = 0; i < MESES.length; i++)
				if (mes2.equalsIgnoreCase(MESES[i])){
					mes1 = i+1;
				}

			if (mes1 == -1)
				System.out.println("Mes invalido");
			if (dia == 31 && (mes2.equalsIgnoreCase(MESES[3]) || mes2.equalsIgnoreCase(MESES[5]) || mes2.equalsIgnoreCase(MESES[8]) || mes2.equalsIgnoreCase(MESES[10]))) {
				System.out.println("Este mes no tiene 31 dias");
				mesValido = false;
			}
			if (dia > 29 && mes2.equalsIgnoreCase(MESES[1]))
				mesValido = false;
		} while(mes1 == -1	|| mesValido == false);

		do {
		System.out.print("Año: ");
		anio = in.nextInt();

		if (anio == 0)
				System.out.println("Año invalido");
		}while(anio == 0);

		if (dia == 29 && mes2.equalsIgnoreCase("febrero")) {
			if (anio % 4 == 0) {
				if (anio % 100 == 0 && anio % 400 == 0) ;
			} else {
				System.out.println("No es año bisciesto");
				System.exit(0);
			}
		}

		int a,y,m;
		a = (14-mes1)/12;
		y = anio + 4800 - 	a;
		m = mes1 +(12*a) - 3;	

		int gregoriano= dia+((153*m + 2)/5) + (365*y) + (y/4) - (y/100)+ (y/400) - 32045;
		int juliano = dia + ((153*m + 2)/5) + (365*y)+(y/4) - 32083;

		int calendario = 0;
		if (anio <= 1923 && anio >= 1582) {
			System.out.println("Juliano(J) o Gregoriano(G)");
			String temp = in.next();
			if (temp.equalsIgnoreCase("J"))
				calendario = juliano;
			else if (temp.equalsIgnoreCase("G"))
				calendario = gregoriano;
			else {
				System.out.println("Opcion Invalida. Se tomara el Juliano");
				calendario = juliano;
			}
		} else if (anio > 1923)
			calendario = gregoriano;
		else
			calendario = juliano;

 		int diasSumados = calendario + 5757;

   		int numeroDia = ((diasSumados - 6) % 13) + 1;
   		numeroDia = numeroDia;

		int  nombreDia = ((diasSumados - 1) % 20) + 1;
   		nombreDia = nombreDia;

		final int CONSTANTEJULIANO = 162;
		final int CONSTANTE = (CONSTANTEJULIANO % 365) - CONSTANTEJULIANO;

		int anioTemp = (diasSumados - 39 + CONSTANTE) % 365;
		int numeroAnio = ((diasSumados + 353  - anioTemp) % 13) + 1;
		int nombreAnio = ((diasSumados +358  - anioTemp) % 20) + 1;

		int trecenas = nombreDia + 1 - numeroDia;
		if (trecenas < 1) {
			trecenas = trecenas + 20;
		}

		System.out.println("\nAño solar:\t" + numeroAnio + " " + SIGNOS[nombreAnio]);
		System.out.println("Trecena:\t" + SIGNOS[trecenas]);
		System.out.println("Día:\t\t" + numeroDia + " " + SIGNOS[nombreDia]);
	}
}
