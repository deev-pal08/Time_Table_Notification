package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BBAFifthSemesterSubjects extends AppCompatActivity {
    String BBA5TH1[]={"BBAN 501","BBAN 502", "BBAN 503","BBAN 504", "BBAN 505"};
    String BBA5TH2[]={"FINANCIAL MANAGEMENT","MARKETING MANAGEMENT","HUMAN RESOURCE MANAGEMENT",
    "ENTREPRENEURSHIP DEVELOPMENT","RESEARCH METHODOLOGY"};
    int BBA5THIMAGE[]={R.drawable.financialmanagement,R.drawable.marketingmanagement,
    R.drawable.humanresource,R.drawable.entreprenuership,R.drawable.researchmethodology};

    String BMS5TH1[] = { "BMS 501" , "BMS 502" , "BMS 503" , "BMS 504" };
    String BMS5TH2[] = { "MARKETING RESEARCH AND METHODOLOGY" , "ENTREPRENEURSHIP AND MEDIA MANAGEMENT" ,
            "ECOLOGY AND ENVIRONMENTAL COMMUNICATION" , "SPECIAL PAPERS (CHOOSE ANY 1)"};
    int BMS5THIMAGE[]={R.drawable.digital_marketing,R.drawable.media_managemnet,
            R.drawable.environment,R.drawable.special_papers};

    String BMS1ST1[] = { "BMS 101" , "BMS 102" , "BMS 103" , "BMS 104" , "BMS 105" , "BMS 191" , "BMS 192" ,
            "BMS 193" , "BMS 181" };
    String BMS1ST2[] = { " INTRODUCTION TO MEDIA " , " LANGUAGE PRACTICE: READING COMPREHENSION AND WRITING " , " PHOTOGRAPHY " ,
            " BASIC COMPUTER APPLICATION " , " VISUAL DESIGN: ASTHETICS AND APPLICATION " , " PHOTOGRAPHY " ,
            " BASIC COMPUTER APPLICATION " , " VISUAL DESIGN: ASTHETICS AND APPLICATION " , " WORLD HISTORY AND CURRENT AFFAIRS I " };
    int BMS1STIMAGE[]={R.drawable.media,R.drawable.language, R.drawable.photography,R.drawable.computer_application,
            R.drawable.visual_design,R.drawable.photography, R.drawable.computer_application,R.drawable.visual_design,
            R.drawable.world_history};

    String BMS4TH1[] = { " BMS 401 " , " BMS 402 " , " BMS 403 " , " BMS 404 " , "BMS 405 " , " BMS 491 " , "BMS 492 " , " BMS 493 " };
    String BMS4TH2[] = { " DIGITAL MEDIA " , " ADVANCED TELEVISION STUDIES " , " INTEGRATED MARKETING COMMUNICATIONS II " ,
            " FILM STUDIES II + FILM DIARY " , " UNDERSTANDING STAGE PORDUCTION " , " ADVANCED TELEVISION STUDIES LAB " ,
            " FILM MAKING II " , " STAGE PRODUCTION " };
    int BMS4THIMAGE[]={R.drawable.digital_media,R.drawable.advanced_teevision, R.drawable.integrated_marketing,R.drawable.film_studies,
            R.drawable.stage_production,R.drawable.advanced_teevision, R.drawable.film_making2,R.drawable.stage_production};

    String BMS2ND1[] = { " BMS 201 " , " BMS 202 " , " BMS 203 " , " BMS 204 " , "BMS 291 " , " BMS 281 " };
    String BMS2ND2[] = { " ELECTRONIC MEDIA PLANNING AND PRODUCTION " , " LAWS AND ETHICS OF MEDIA " , " PRINT MEDIA " ,
            " WRITING FOR MEDIA " , " ELECTRONIC MEDIA PLANNING AND PRODUCTION " , " WORLD HISTORY AND CURRENT AFFAIRS II " };
    int BMS2NDIMAGE[]={R.drawable.electronic_media,R.drawable.lawsandethicsofmedia, R.drawable.print_media,R.drawable.writing_for_media,
            R.drawable.electronic_media,R.drawable.world_history};

    String BMS6TH1[] = { " BMS 691"};
    String BMS6TH2[] = { " SPECIAL PAPERS"};
    int BMS6THIMAGE[]={R.drawable.special_papers};

    String BMS3RD1[] = { " BMS 301 " , " BMS 302 " , " BMS 391 " , " BMS 392 " , "BMS 393 " , " BMS 381 " };
    String BMS3RD2[] = { " INTEGRATED MARKETING COMMUNICATION I " , " FILM STUDIES I + FILM DIARY " ,
            " FILM MAKING 1 (LAB) " , " ADVANCED PHOTOGRAPHY LAB " , " DESIGN PAGE LAYOUT LAB " ,
            " WORLD HISTORY AND CURRENT AFFAIRS III " };
    int BMS3RDIMAGE[]={R.drawable.integrated_marketing,R.drawable.film_diary, R.drawable.film_making2,
            R.drawable.advanced_photography, R.drawable.design_page,R.drawable.world_history};

    String BCA1ST1[] = {"BCAN 101" , "BCAN 102" , "BCAN 103" , "BMN 101" , "BCAN 193" , "BCAN 181"};
    String BCA1ST2[] = {"DIGITAL ELECTRONICS" , "ENVIRONMENT STUDIES" , "C PROGRAMMING" ,
            "MATHEMATICAL COMPUTATION" , "PROGRAMMING LAB WITH C" , "PC SOFTWARE LAB"};

    int BCA1STIMAGE[]={R.drawable.digital_electronics,R.drawable.environmental_studies, R.drawable.c_programming,
            R.drawable.mathsfor_computing, R.drawable.c_programminglab,R.drawable.pc_software};

    String BCA2ND1[] = { " BCAN 201 " , " BCAN 202 " , " BCAN 203 " , " BMN 201 " , " HUN 201 " , " BCAN 293 " , " HUN 291 " };
    String BCA2ND2[] = { " COMPUTER ARCHITECTURE " , " SOFTWARE ENGINEERING " , " DATA STRUCTURE WITH C " ,
            " ADVANCED MATHEMATICAL COMPUTATION " , " ENGLISH LANGUAGE AND COMMUNICATION " ,
            " DATA STRUCTURE LAB USING C " , " BUSINESS PRESENTATION AND LANGUAGE LAB " };

    int BCA2NDIMAGE[]={R.drawable.comp_arch,R.drawable.software_engineering, R.drawable.data_structure,
            R.drawable.ad_maths, R.drawable.eng_comm,R.drawable.data_structure,R.drawable.business_lab};

    String BCA3RD1[] = { " BCAN 301 " , " BCAN E302A " , " BCAN E302B " , " BCAN 303 " , " BMN 301 " , " BCAN E392A " , " BCAN E392B " , " BCAN 381 " };
    String BCA3RD2[] = { " OPERATING SYSTEM " , " OBJECT ORIENTED PROGRAMMING WITH C++ " ,
            " GUI PROGRAMMING WITH .NET " , " COMPUTER GRAPHICS " , " MATHEMATICS FOR COMPUTING " ,
            " PROGRAMMING LAB WITH C++ " , " PROGRAMMING LAB WITH .NET " , " WEB TECHNOLOGY LAB " };

    int BCA3RDIMAGE[]={R.drawable.comp_arch,R.drawable.cplus, R.drawable.dotnet,
            R.drawable.computer_graphics, R.drawable.mathsfor_computing,R.drawable.c_programminglab,
            R.drawable.dotnet,R.drawable.c_programminglab};

    String BCA4TH1[] = { " BCAN 401 " , " BCAN 402 " , " BCAN 403 " , " BMN 401 " , " BCAN 491 " , " BCAN 492 " , " BCAN 481 " };
    String BCA4TH2[] = { " DATABASE MANAGEMENT SYSTEM " , " PROGRAMMING WITH JAVA " , " COMPUTER NETWORKING " ,
            " NUMERICAL ANALYSIS " , " DATABASE LAB " , " PROGRAMMING LAB WITH JAVA " , " WEB TECHNOLOGY LAB " };

    int BCA4THIMAGE[]={R.drawable.database_lab,R.drawable.java, R.drawable.comp_networking,
            R.drawable.numerical_analysis, R.drawable.database_lab,R.drawable.java_lab,
            R.drawable.database_lab};

    String BCA5TH1[] = { " BCAN 501 " , " BCAN 502 " , " BCA(BBA)N 501 " ,  " BCAN 592 "  };
    String BCA5TH2[] = { " CYBER SECURITY " , " UNIX AND SHELL PROGRAMMING " , " MANAGEMENT AND ACCOUNTING " ,
             " LINUX LAB " };

    int BCA5THIMAGE[]={R.drawable.cyber_security,R.drawable.unix, R.drawable.marketingmanagement,
            R.drawable.linux_lab};

    String BCA6TH1[] = { " BCAN E601A " , " BCAN E601B " , " BCAN E601C " , " BCAN E602A " , " BCAN E602B " ,
            " BCAN E602C " , " HUN 601 "};
    String BCA6TH2[] = { " PYTHON PROGRAMMING " , " ARTIFICIAL INTELLIGENCE " , " E-COMMERCE " ,
            " WEB TECHNOLOGY WITH PHP " , " MYSQL ADVANCED DBMS WITH PLSQL " , " DIGITAL MARKETING " ,};

    int BCA6THIMAGE[]={R.drawable.python,R.drawable.ai, R.drawable.ecommerce,
            R.drawable.php, R.drawable.my_sql,R.drawable.digital_marketing};
    RecyclerView rView;
    GridLayoutManager manager;
    SubjectStructure adapter;
    String course,position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_b_a_fifth_semester_subjects);

       Intent intent=getIntent();
       course=intent.getStringExtra("CourseName");
        //Toast.makeText(this, course, Toast.LENGTH_LONG).show();
       position = course.substring(5);
       course=course.substring(0,5);
        //Toast.makeText(this, course, Toast.LENGTH_LONG).show();
        rView=findViewById(R.id.rcViewSub);
        manager=new GridLayoutManager(BBAFifthSemesterSubjects.this,1);
        rView.setLayoutManager(manager);
        //Log.i("TAG", "onCreate:"+course);

        if(course.equals("BCA1A")||course.equals("BCA1B"))
        adapter = new SubjectStructure(this, BCA1ST1,BCA1ST2,position,course);
        else if(course.equals("BCA2A")||course.equals("BCA2B"))
            adapter = new SubjectStructure(this, BCA2ND1,BCA2ND2,position,course);
        else if(course.equals("BCA3A")||course.equals("BCA3B"))
            adapter = new SubjectStructure(this, BCA3RD1,BCA3RD2,position,course);
        else if(course.equals("BCA4A")||course.equals("BCA4B"))
            adapter = new SubjectStructure(this, BCA4TH1,BCA4TH2,position,course);
        else if(course.equals("BCA5A")||course.equals("BCA5B"))
            adapter = new SubjectStructure(this, BCA5TH1,BCA5TH2,position,course);
        else if(course.equals("BCA6A")||course.equals("BCA6B"))
            adapter = new SubjectStructure(this, BCA6TH1,BCA6TH2,position,course);
        else if(course.equals("BMS1A")||course.equals("BMS1B"))
            adapter = new SubjectStructure(this, BMS1ST1,BMS1ST2,position,course);
        else if(course.equals("BMS2A")||course.equals("BMS2B"))
            adapter = new SubjectStructure(this, BMS2ND1,BMS2ND2,position,course);
        else if(course.equals("BMS3A")||course.equals("BMS3B"))
            adapter = new SubjectStructure(this, BMS3RD1,BMS3RD2,position,course);
        else if(course.equals("BMS4A")||course.equals("BMS4B"))
            adapter = new SubjectStructure(this, BMS4TH1,BMS4TH2,position,course);
        else if(course.equals("BMS5A")||course.equals("BMS5B"))
            adapter = new SubjectStructure(this, BMS5TH1,BMS5TH2,position,course);
        else if(course.equals("BMS6A")||course.equals("BMS6B"))
            adapter = new SubjectStructure(this, BMS6TH1,BMS6TH2,position,course);
        else if(course.equals("BBA1A")||course.equals("BBA1B"))
            adapter = new SubjectStructure(this, BBA5TH1,BBA5TH2,position,course);
        else if(course.equals("BBA2A")||course.equals("BBA2B"))
            adapter = new SubjectStructure(this, BBA5TH1,BBA5TH2,position,course);
        else if(course.equals("BBA3A")||course.equals("BBA3B"))
            adapter = new SubjectStructure(this, BBA5TH1,BBA5TH2,position,course);
        else if(course.equals("BBA4A")||course.equals("BBA4B"))
            adapter = new SubjectStructure(this, BBA5TH1,BBA5TH2,position,course);
        else if(course.equals("BBA5A")||course.equals("BBA5B"))
            adapter = new SubjectStructure(this, BBA5TH1,BBA5TH2,position,course);
        else if(course.equals("BBA6A")||course.equals("BBA6B"))
            adapter = new SubjectStructure(this, BBA5TH1,BBA5TH2,position,course);
        rView.setAdapter(adapter);
    }
}