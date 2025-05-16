package com.ifsc.contaclick;

import java.util.ArrayList;

public class DAOPlaneta {

    ArrayList<Planeta> listplanetas ;

    public DAOPlaneta() {
        listplanetas = new ArrayList<>();
        String []  planetas = new String[] {"Mercurio", "Venus", "Terra", "Marte", "jupter", "Saturno", "Urano","Netuno"};
        Integer[]  fotos = new Integer[] {R.drawable.mercury,R.drawable.venus,R.drawable.earth,
                R.drawable.mars,R.drawable.jupter,R.drawable.saturn,
                R.drawable.uranus,R.drawable.neptune};
        for(int i=0 ; i<planetas.length;i++){
            listplanetas.add(new Planeta(planetas[i],fotos[i]));
        }

    }
}
