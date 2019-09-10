package com.filano.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button numNol, numSatu, numDua, numTiga, numEmpat, numLima, numEnam, numTujuh, numDelapan, numSembilan, numTitik;
    private Button opClear, opBagi, opKali, opKurang, opTambah, opSamadengan;
    private TextView tvDisplay, tvCalculation;

    private String number = "";
    private Double firstNum, lastNum, result;
    private boolean coma= false, bagi=false, kurang= false, tambah= false, kali= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numNol = findViewById(R.id.numNol);
        numSatu = findViewById(R.id.numSatu);
        numDua = findViewById(R.id.numDua);
        numTiga = findViewById(R.id.numTiga);
        numEmpat = findViewById(R.id.numEmpat);
        numLima = findViewById(R.id.numLima);
        numEnam = findViewById(R.id.numEnam);
        numTujuh = findViewById(R.id.numTujuh);
        numDelapan = findViewById(R.id.numDelapan);
        numSembilan = findViewById(R.id.numSembilan);
        numTitik = findViewById(R.id.numTitik);
        opClear = findViewById(R.id.opClear);
        opBagi = findViewById(R.id.opBagi);
        opKali = findViewById(R.id.opKali);
        opKurang = findViewById(R.id.opKurang);
        opTambah = findViewById(R.id.opTambah);
        opSamadengan = findViewById(R.id.opSamaDengan);
        tvDisplay = findViewById(R.id.tvDisplay);
        tvCalculation = findViewById(R.id.tvCalculation);

        numNol.setOnClickListener(this);
        numSatu.setOnClickListener(this);
        numDua.setOnClickListener(this);
        numTiga.setOnClickListener(this);
        numEmpat.setOnClickListener(this);
        numLima.setOnClickListener(this);
        numEnam.setOnClickListener(this);
        numTujuh.setOnClickListener(this);
        numDelapan.setOnClickListener(this);
        numSembilan.setOnClickListener(this);
        numTitik.setOnClickListener(this);
        opClear.setOnClickListener(this);
        opBagi.setOnClickListener(this);
        opKali.setOnClickListener(this);
        opKurang.setOnClickListener(this);
        opTambah.setOnClickListener(this);
        opSamadengan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.numNol:
                number = number + "0";
                tvDisplay.setText(tvDisplay.getText() + "0");
                break;
            case R.id.numSatu:
                number = number + "1";
                tvDisplay.setText(tvDisplay.getText() + "1");
                break;
            case R.id.numDua:
                number = number + "2";
                tvDisplay.setText(tvDisplay.getText() + "2");
                break;
            case R.id.numTiga:
                number = number + "3";
                tvDisplay.setText(tvDisplay.getText() + "3");
                break;
            case R.id.numEmpat:
                number = number + "4";
                tvDisplay.setText(tvDisplay.getText() + "4");
                break;
            case R.id.numLima:
                number = number + "5";
                tvDisplay.setText(tvDisplay.getText() + "5");
                break;
            case R.id.numEnam:
                number = number + "6";
                tvDisplay.setText(tvDisplay.getText() + "6");
                break;
            case R.id.numTujuh:
                number = number + "7";
                tvDisplay.setText(tvDisplay.getText() + "7");
                break;
            case R.id.numDelapan:
                number = number + "8";
                tvDisplay.setText(tvDisplay.getText() + "8");
                break;
            case R.id.numSembilan:
                number = number + "9";
                tvDisplay.setText(tvDisplay.getText() + "9");
                break;
            case R.id.numTitik:
                if(!number.isEmpty() && !coma){
                    coma= true;
                    number = number + ".";
                    tvDisplay.setText(tvDisplay.getText() + ".");
                }
                break;

            case R.id.opClear:
                firstNum = null;
                lastNum = null;
                bagi = false;
                kali = false;
                kurang = false;
                tambah = false;
                number="";
                coma = false;
                tvCalculation.setText("");
                tvDisplay.setText("");
                break;
            case R.id.opBagi:
                if (!number.isEmpty() && !tambah && !kurang && !kali && !bagi){
                    coma = false;
                    bagi = true;
                    firstNum = Double.parseDouble(number);
                    number = "";
                    tvDisplay.setText(tvDisplay.getText() + "÷");
                }
                break;
            case R.id.opKali:
                if (!number.isEmpty() && !tambah && !kurang && !kali && !bagi){
                    coma = false;
                    kali = true;
                    firstNum = Double.parseDouble(number);
                    number = "";
                    tvDisplay.setText(tvDisplay.getText() + "×");
                }
                break;
            case R.id.opKurang:
                if (!number.isEmpty() && !tambah && !kurang && !kali && !bagi){
                    coma = false;
                    kurang = true;
                    firstNum = Double.parseDouble(number);
                    number = "";
                    tvDisplay.setText(tvDisplay.getText() + "−");
                }
                break;
            case R.id.opTambah:
                if (!number.isEmpty() && !tambah && !kurang && !kali && !bagi){
                    coma = false;
                    tambah = true;
                    firstNum = Double.parseDouble(number);
                    number = "";
                    tvDisplay.setText(tvDisplay.getText() + "+");
                }
                break;
            case R.id.opSamaDengan:
                if (!number.isEmpty() && (firstNum != null)){
                    lastNum = Double.parseDouble(number);

                    if(bagi){
                        result = firstNum / lastNum;
                        tvCalculation.setText(firstNum.toString()+ " ÷ " +lastNum.toString());
                        bagi = false;
                    }else if(kali){
                        result = firstNum * lastNum;
                        tvCalculation.setText(firstNum.toString()+ " × " +lastNum.toString());
                        kali = false;
                    }else if (kurang){
                        result = firstNum - lastNum;
                        tvCalculation.setText(firstNum.toString()+ " − " +lastNum.toString());
                        kurang = false;
                    }else if(tambah){
                        result = firstNum + lastNum;
                        tvCalculation.setText(firstNum.toString()+ " + " +lastNum.toString());
                        tambah = false;
                    }

                    tvDisplay.setText(result.toString());
                    firstNum = null;
                    lastNum = null;
                    coma = true;
                    number = result.toString();
                }
                break;
        }
    }
}
