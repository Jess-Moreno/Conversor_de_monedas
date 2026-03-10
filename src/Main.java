import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaAPI consulta = new ConsultaAPI();
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("******************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda");
            System.out.println("1) Dólar [USD] =>> Peso colombiano [COP]");
            System.out.println("2) Peso colombiano [COP] =>> Dólar [USD]");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");

            if (lectura.hasNextInt()) {
                opcion = lectura.nextInt();
            } else {
                System.out.println("Por favor, ingrese un número.");
                lectura.next();
                continue;
            }

            if (opcion == 7) break;

            System.out.println("Ingrese el valor que desea convertir:");
            double valor = lectura.nextDouble();

            if (opcion == 1) {
                convertir(consulta, "USD", "COP", valor);
            } else if (opcion == 2) {
                convertir(consulta, "COP", "USD", valor);
            } else {
                System.out.println("Opción no válida.");
            }
        }
        System.out.println("Programa finalizado.");
    }
    private static void convertir(ConsultaAPI consulta, String base, String destino, double valor) {
        try {
            DatosMoneda datos = consulta.obtenerTasas(base);

            if (datos != null && datos.conversion_rates() != null) {
                double tasa = datos.conversion_rates().get(destino);
                double resultado = valor * tasa;
                System.out.println("******************************************");
                System.out.println("Resultado: " + valor + " [" + base + "] equivale a " + resultado + " [" + destino + "]");
                System.out.println("******************************************");
            }
        } catch (Exception e) {
            System.out.println("¡Ups! Algo salió mal: " + e.getMessage());
        }
    }
}