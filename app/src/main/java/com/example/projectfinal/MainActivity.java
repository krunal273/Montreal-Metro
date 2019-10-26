package com.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSearch, btnClear;
    AutoCompleteTextView autoCompleteTextViewSource, autoCompleteTextViewDestination;
    ArrayList<String> allStation = new ArrayList<String>();
    ArrayList<String> orangeLine = new ArrayList<String>();
    ArrayList<String> greenLine = new ArrayList<String>();
    ArrayList<String> blueLine = new ArrayList<String>();
    ArrayList<String> path = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilize();
    }

    private void initilize() {
        autoCompleteTextViewSource = findViewById(R.id.autoCompleteTextViewSource);
        autoCompleteTextViewDestination = findViewById(R.id.autoCompleteTextViewDestination);
        btnSearch = findViewById(R.id.btnSearch);
        btnClear = findViewById(R.id.btnClear);
        btnSearch.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        orangeLine.addAll(Arrays.asList("cote vertu", "du college", "de la savane", "namur", "plamondon",
                "cote sainte catherine", "snowdon", "villa maria", "vendome", "place saint henri",
                "Lionel Groulx", "georges vanier", "lucien lallier", "Bonaventure", "Square Victoria OACI",
                "Place dArmes", "Champ de Mars", "Berri UQAM", "Sherbrooke", "Mont Royal", "Laurier",
                "rosemont", "Beaubien", "Jean Talon", "jarry", "Crémazie", "Sauvé",
                "Henri Bourassa", "Cartier", "De La Concorde", "Montmorency"));

        greenLine.addAll(Arrays.asList("Angrignon", "Monk", "Jolicoeur", "Verdun",
                "De leglise", "LaSalle", "Charlevoix", "Lionel Groulx", "Atwater", "Guy Concordia", "Peel",
                "McGill", "Place des Arts", "Saint Laurent", "Berri UQAM", "Beaudry", "Papineau", "Frontenac",
                "Prefontaine", "Joliette", "Pie IX", "Viau", "Assomption", "Cadillac", "Langelier", "Radisson", "Honore Beaugrand"
        ));

        blueLine.addAll(Arrays.asList("snowdon", "Cote des Neiges", "Universite de Montreal", "edouard Montpetit",
                "outremont", "acadie", "parc", "De Castelnau", "Jean Talon", "Fabre",
                "DIberville", "Saint-Michel"));

        allStation.addAll(orangeLine);
        allStation.addAll(greenLine);
        allStation.addAll(blueLine);
        autoCompleteTextViewSource.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allStation));
        autoCompleteTextViewDestination.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allStation));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnSearch:
                String source = autoCompleteTextViewSource.getText().toString();
                String destination = autoCompleteTextViewDestination.getText().toString();
                int startIndex;
                int endIndex;
                Log.i("Tag1", "Source " + source + " Destination " + destination);

                if (orangeLine.contains(source) && orangeLine.contains(destination)) {
                    startIndex = orangeLine.indexOf(source);
                    endIndex = orangeLine.indexOf(destination);
                    goTowards(orangeLine, startIndex, endIndex);

                } else if (greenLine.contains(source) && greenLine.contains(destination)) {
                    startIndex = greenLine.indexOf(source);
                    endIndex = greenLine.indexOf(destination);
                    goTowards(greenLine, startIndex, endIndex);
                } else if (blueLine.contains(source) && blueLine.contains(destination)) {
                    startIndex = blueLine.indexOf(source);
                    endIndex = blueLine.indexOf(destination);
                    goTowards(blueLine, startIndex, endIndex);
                } else if ((orangeLine.contains(source) && blueLine.contains(destination)) || (blueLine.contains(source) && orangeLine.contains(destination))) {

                    if (orangeLine.indexOf(source) != -1) {
                        // Start From Orange Line
                        String interSectionName;
                        startIndex = orangeLine.indexOf(source);
                        endIndex = blueLine.indexOf(destination);
                        if (startIndex < orangeLine.indexOf("Jean Talon")) {
                            interSectionName = "snowdon";
                        } else {
                            interSectionName = "Jean Talon";
                        }
                        goTowardsInterSection(orangeLine, blueLine, startIndex, endIndex, interSectionName);

                    } else {
                        // Start From Blue Line
                        String interSectionName = "snowdon";
                        startIndex = blueLine.indexOf(source);
                        endIndex = orangeLine.indexOf(destination);
//                        if (startIndex < blueLine.indexOf("Jean Talon")) {
//                            interSectionName = "Jean Talon";
//                        } else {
//                            interSectionName = "snowdon";
//                        }
                        goTowardsInterSection(blueLine, orangeLine, startIndex, endIndex, interSectionName);
                    }
                } else if ((orangeLine.contains(source) && greenLine.contains(destination)) || (greenLine.contains(source) && orangeLine.contains(destination))) {

                    if (orangeLine.indexOf(source) != -1) {
                        // Start From Orange Line
                        String interSectionName;
                        startIndex = orangeLine.indexOf(source);
                        endIndex = greenLine.indexOf(destination);
                        if (startIndex < orangeLine.indexOf("Berri UQAM")) {
                            interSectionName = "Lionel Groulx";
                        } else {
                            interSectionName = "Berri UQAM";
                        }
                        goTowardsInterSection(orangeLine, greenLine, startIndex, endIndex, interSectionName);

                    } else {
                        // Start From Green Line
                        String interSectionName;
                        startIndex = greenLine.indexOf(source);
                        endIndex = orangeLine.indexOf(destination);
                        if (startIndex < greenLine.indexOf("Lionel Groulx")) {
                            interSectionName = "Lionel Groulx";
                        } else {
                            interSectionName = "Berri UQAM";
                        }
                        goTowardsInterSection(greenLine, orangeLine, startIndex, endIndex, interSectionName);
                    }

                } else if ((blueLine.contains(source) && greenLine.contains(destination)) || (greenLine.contains(source) && blueLine.contains(destination))) {

                    if (blueLine.indexOf(source) != -1) {
                        // Start From Blue Line
                        String interSectionBlue;
                        String interSectionGreeen;
                        startIndex = blueLine.indexOf(source);
                        endIndex = greenLine.indexOf(destination);
                        if (startIndex < blueLine.indexOf("snowdon")) {
                            interSectionBlue = "snowdon";
                        } else {
                            interSectionBlue = "Jean Talon";
                        }

                        if (endIndex < greenLine.indexOf("Lionel Groulx")) {
                            interSectionGreeen = "Lionel Groulx";
                        } else {
                            interSectionGreeen = "Berri UQAM";
                        }

                        goTowardsGreenBlue(blueLine, greenLine, orangeLine, startIndex, endIndex, interSectionBlue, interSectionGreeen);
                    } else {
                        // Start From Green Line
                        String interSectionBlue;
                        String interSectionGreeen;
                        startIndex = greenLine.indexOf(source);
                        endIndex = blueLine.indexOf(destination);

                        if (startIndex < greenLine.indexOf("Lionel Groulx")) {
                            interSectionGreeen = "Lionel Groulx";
                        } else {
                            interSectionGreeen = "Berri UQAM";
                        }

                        if (endIndex < blueLine.indexOf("snowdon")) {
                            interSectionBlue = "snowdon";
                        } else {
                            interSectionBlue = "Jean Talon";
                        }
                        goTowardsGreenBlue(greenLine, blueLine, orangeLine, startIndex, endIndex, interSectionGreeen, interSectionBlue);
                    }

                } else {
//                    no station found
                    Toast.makeText(this, "Error No Station Found", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnClear:
                autoCompleteTextViewSource.setText("");
                autoCompleteTextViewDestination.setText("");
                break;
            default:
                break;
        }
    }

    private void goTowardsGreenBlue(ArrayList<String> lineOneStart, ArrayList<String> lineTwoEnd, ArrayList<String> lineMiddle, int startIndex, int endIndex, String interSectionOne, String interSectionTwo) {
        path.clear();
        // First Line
        if (startIndex < lineOneStart.indexOf(interSectionOne)) {
            // go towords
            for (int i = startIndex; i <= lineOneStart.indexOf(interSectionOne); i++) {
                path.add(lineOneStart.get(i));
            }
        } else {
            // go back
            for (int i = startIndex; i >= lineOneStart.indexOf(interSectionOne); i--) {
                path.add(lineOneStart.get(i));
            }
        }

        // Middle Line
        if (lineMiddle.indexOf(interSectionOne) > lineMiddle.indexOf(interSectionTwo)) {
            // go towords

//            Log.i("Tag1", "interSectionOne " + lineMiddle.indexOf(interSectionOne) + "\ninterSectionTwo" +
//                    lineMiddle.indexOf(interSectionTwo));

            for (int i = lineMiddle.indexOf(interSectionOne); i >= lineMiddle.indexOf(interSectionTwo); i--) {
                path.add(lineMiddle.get(i));
            }
        } else {
            // go back
            for (int i = lineMiddle.indexOf(interSectionOne); i <= lineMiddle.indexOf(interSectionTwo); i++) {
                path.add(lineMiddle.get(i));
            }
        }

        // End Line
        if (endIndex > lineTwoEnd.indexOf(interSectionTwo)) {
            // go towords
            for (int i = lineTwoEnd.indexOf(interSectionTwo); i <= endIndex; i++) {
                path.add(lineTwoEnd.get(i));
            }
        } else {
            // go back
            for (int i = lineTwoEnd.indexOf(interSectionTwo); i >= endIndex; i--) {
                path.add(lineTwoEnd.get(i));
            }
        }

        Log.i("Tag2", "\nStart Index : " + startIndex +
                "\nEnd Index " + endIndex + "\nPath " + path);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }

    private void goTowardsInterSection(ArrayList<String> lineOneStart, ArrayList<String> lineTwoEnd, int startIndex, int endIndex, String inteSectionName) {
        path.clear();
        // First Line
        if (startIndex < lineOneStart.indexOf(inteSectionName)) {
            // go towords
            for (int i = startIndex; i <= lineOneStart.indexOf(inteSectionName); i++) {
                path.add(lineOneStart.get(i));
            }
        } else {
            // go back
            for (int i = startIndex; i >= lineOneStart.indexOf(inteSectionName); i--) {
                path.add(lineOneStart.get(i));
            }
        }

        // Second Line
        if (endIndex > lineTwoEnd.indexOf(inteSectionName)) {
            // go towords
            for (int i = lineTwoEnd.indexOf(inteSectionName); i <= endIndex; i++) {
                path.add(lineTwoEnd.get(i));
            }
        } else {
            // go back
            for (int i = lineTwoEnd.indexOf(inteSectionName); i >= endIndex; i--) {
                path.add(lineTwoEnd.get(i));
            }
        }

        Log.i("Tag2", "\nStart Index : " + startIndex +
                "\nEnd Index " + endIndex + "\nPath " + path);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }

    private void goTowards(ArrayList<String> line, int startIndex, int endIndex) {
        path.clear();
        if (startIndex < endIndex) {
            for (int i = startIndex; i <= endIndex; i++) {
                path.add(line.get(i));
            }
        } else {
            for (int i = startIndex; i >= endIndex; i--) {
                path.add(line.get(i));
            }
        }

        Log.i("Tag2", "\nStart Index : " + startIndex +
                "\nEnd Index " + endIndex + "\nPath " + path);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }
}
