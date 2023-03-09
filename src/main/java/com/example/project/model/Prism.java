package com.example.project.model;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Prism {
    public static void main(String[] args){
        try {
            PrintWriter writer = new PrintWriter("diabetes_model.prism", StandardCharsets.UTF_8);
            //dupa ce mai face lumea o sa mai iau date din front si o sa compar si astfel sa modific chestiile pe care le am in codul de la prism, pentru fie
            // fiecare caz sa scrie unele lucruri, altfel altele

            //ca sa scapam intre

            // Constants
            int BMI_UNDERWEIGHT = 1;
            int BMI_NORMAL = 2;
            int BMI_OVERWEIGHT = 3;
            int BMI_OBESE = 4;

            int GENDER_MALE = 1;
            int GENDER_FEMALE = 2;

            // Write model
            writer.println("dtmc");
            writer.println();
            writer.println("const int BMI_UNDERWEIGHT = 1;");
            writer.println("const int BMI_NORMAL = 2;");
            writer.println("const int BMI_OVERWEIGHT = 3;");
            writer.println("const int BMI_OBESE = 4;");
            writer.println();
            writer.println("const int GENDER_MALE = 1;");
            writer.println("const int GENDER_FEMALE = 2;");
            writer.println();
            writer.println("module diabetes");
            writer.println("BMI : [BMI_UNDERWEIGHT..BMI_OBESE] init BMI_NORMAL;");
            writer.println("gender : [GENDER_MALE..GENDER_FEMALE] init GENDER_MALE;");
            writer.println("age : [0..100] init 30;");
            writer.println("family_history : bool init true;");
            writer.println("diabetes : bool init false;");
            writer.println();
            writer.println("[obese] BMI = BMI_OBESE & !diabetes & family_history=true -> 0.9 : (diabetes' = true) + 0.1 : (diabetes' = false);");
            writer.println("[overweight] BMI = BMI_OVERWEIGHT -> 0.3 : (diabetes' = true) + 0.7 : (diabetes' = false);");
            writer.println("[normal] BMI = BMI_NORMAL -> 0.1 : (diabetes' = true) + 0.9 : (diabetes' = false);");
            writer.println("[underweight] BMI = BMI_UNDERWEIGHT -> 0.01 : (diabetes' = true) + 0.99 : (diabetes' = false);");
            writer.println("[female] gender = GENDER_FEMALE -> 0.15 : (diabetes' = true) + 0.85 : (diabetes' = false);");
            writer.println("[family] family_history = true -> 0.4 : (diabetes' = true) + 0.6 : (diabetes' = false);");
            writer.println("[age] age > 40 -> 0.2 : (diabetes' = true) + 0.8 : (diabetes' = false);");
            writer.println();

            writer.println("[gain_weight] BMI = BMI_NORMAL & age > 30 -> 0.5 : (BMI' = BMI_OVERWEIGHT) + 0.5 : (BMI' = BMI_NORMAL);");
            writer.println("[lose_weight] BMI = BMI_OVERWEIGHT & age > 30 -> 0.5 : (BMI' = BMI_NORMAL) + 0.5 : (BMI' = BMI_OVERWEIGHT);");
            writer.println("[age_transition] age < 50 -> 0.5 : (age' = age + 1) + 0.5 : (age' = age);");
            writer.println();
            writer.println("endmodule");
            writer.close();
        }catch(Exception e) {
            e.getStackTrace();
        }
    }
}