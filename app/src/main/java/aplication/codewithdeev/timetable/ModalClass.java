package aplication.codewithdeev.timetable;

import java.util.HashMap;
import java.util.Map;

public class ModalClass {

    String Timming,SubjectName,SubjectTecaher,SubjectCode,position;

    public ModalClass() {
    }

    public ModalClass(String timming, String subjectName, String subjectTecaher, String subjectCode,String position) {
        Timming = timming;
        SubjectName = subjectName;
        SubjectTecaher = subjectTecaher;
        SubjectCode = subjectCode;
        this.position=position;
    }

    public String getPosition() {
        return position;
    }

    public String getTimming() {
        return Timming;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getSubjectTecaher() {
        return SubjectTecaher;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setTimming(String timming) {
        Timming = timming;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public void setSubjectTecaher(String subjectTecaher) {
        SubjectTecaher = subjectTecaher;
    }

    public void setSubjectCode(String subjectCode) {
        SubjectCode = subjectCode;
    }
}