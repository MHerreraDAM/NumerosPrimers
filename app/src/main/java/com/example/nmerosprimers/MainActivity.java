package com.example.nmerosprimers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  static EditText edit_posicion; //Posición que introduce el usuario para calcular el número entero
    private static Button button_calcular; //Botón calcular
    private static TextView text_respuesta; //Salida de la respuesta tras calcular



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vincular id's
        edit_posicion = findViewById(R.id.posicion);
        button_calcular = findViewById(R.id.calcular);
        text_respuesta = findViewById(R.id.resultado);

        ////////////////////////////////////////////////////////
        // APP
        ////////////////////////////////////////////////////////
        //Quan es toca el botó: Calcular:
        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_respuesta.setText(appPrimos());

            }
        });

    }


    //GETTERS
    public EditText getEdit_posicion() {
        return edit_posicion;
    }

    public TextView getText_respuesta() {
        return text_respuesta;
    }

    ////////////////////////////////////////////////////////
    // MÉTODOS
    ////////////////////////////////////////////////////////

    //Retorna true si el número es primo y false si no lo es
    public static boolean esPrimo(int num) {
        boolean primo = (num == 1) ? false : true;
        int divisor = 2;
        while (primo && divisor <= Math.sqrt(num)) {
            if (num % divisor == 0) {
                primo = false;
            }
            divisor++;
        }
        return primo;
    }

    //Calcula el nnúmero primo según la posición que se le indique
    public static int calculadoraPrimos(int posicion) {
        int esPrimoContador = 0; //Cuenta los números primos que encuentra

        for (int n = 2; n >= 2; n++) { //Se comienza por el 2, ya que el número 1 no es primo
            if (esPrimo(n)) {
                esPrimoContador++; //Cada vez que se encuentra un primo, sube el contador hasta llegar a la posición que se desea
                if (esPrimoContador == posicion) {
                    return n;
                }
            }
        }
        return 0;
    }

    public static boolean  esNúmero (String cad){ //Para controlar la entrada de la información

        if (cad.matches("[0-9]*")) { //Si la cadena conté només números estarà bé i serà true
            return true;
        } else {
            return false;
        }
    }

    public void guardaResultados (String[] v, int n, String resultado){ //Guarda los resultados que se obtengan
        //V será el vector donde se guardarán los resultados
        //n será la posición del vector dónde se guardará
        //Resultado será el número primo que salga tras hacer las operaciones

        if (n>= v.length){
            n = 0; //Si el vector se llena, comienza a llenar el vector desde el principio
            v[n] = resultado;
        } else{
            v[n] = resultado;
        }
    }

    public void errorCalcular() { //Lanza un toast de error al calcular (cuando los carácteres que escriba el usuario no sean correctos)
        Toast toast3 = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_error,
                (ViewGroup) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView)layout.findViewById(R.id.textCalculado);

        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(layout);
        toast3.show();
    }

    public void calculado() { //Lanza un toast cuando acaba de calcular
        Toast toast3 = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toasts_layaout_calculado,
                (ViewGroup) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView)layout.findViewById(R.id.textCalculado);

        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(layout);
        toast3.show();
    }



    public String appPrimos(){

        //Transformación
        //Transforma EditText a int --> POSICIÓN
        String convertidorTextString =   edit_posicion.getText().toString();

        //Crea un vector dónde se guardarán los resultados
        String v[] = new String[3];
        int nResultat = 0; //Contará los resultados

        int posiciónInt;

        if (convertidorTextString.equals("")){
            errorCalcular(); //Si no ha escrito nada, no podrá continuar
            return "";
        }

        if (esNúmero (convertidorTextString)== true){
            posiciónInt  = Integer.valueOf(convertidorTextString).intValue(); //Si el usuario ha escrito un número correcto, el progrma continuará
        }else{
            errorCalcular(); //Si no ha escrito números, no podrá continuar
            return "";
        }

        if ((posiciónInt >= 1) && (posiciónInt < 9999)) { //Verifica que la posicion está en el rango adecuado
            int resultat = calculadoraPrimos(posiciónInt); //Se ejecuta el método y devuelve el número en cuestión
            String cadResultat = Integer.toString(resultat);
            calculado(); //Mensaje toast

            //Guardar resultado
            guardaResultados(v, nResultat, cadResultat);
            nResultat  ++;

            return cadResultat;
        }

        return "Error";
    }























}