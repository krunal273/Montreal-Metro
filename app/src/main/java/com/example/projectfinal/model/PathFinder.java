package com.example.projectfinal.model;

import android.util.Log;

import java.util.ArrayList;

public class PathFinder {

    public static ArrayList<String> path = new ArrayList<String>();

    public static ArrayList<String> goTowardsGreenBlue(ArrayList<String> lineOneStart, ArrayList<String> lineTwoEnd, ArrayList<String> lineMiddle, int startIndex, int endIndex, String interSectionOne, String interSectionTwo) {
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

        return path;
    }

    public static ArrayList<String> goTowardsInterSection(ArrayList<String> lineOneStart, ArrayList<String> lineTwoEnd, int startIndex, int endIndex, String inteSectionName) {
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
        return path;
    }

    public static ArrayList<String> goTowards(ArrayList<String> line, int startIndex, int endIndex) {
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

        return path;
    }
}
