package com.noobdev.ciap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GiovanniGomes on 04/03/2015.
 */
public class DAL {
    Activity Act;
    SQLiteDatabase mydb;
    private static String DBNAME = "CIAP.db";    // THIS IS THE SQLITE DATABASE FILE NAME.
    private static String TB_CATEGORIA = "TB_CATEGORIA";       // THIS IS THE TABLE NAME
    private static String TB_ITEM = "TB_ITEM";       // THIS IS THE TABLE NAME

    public DAL(Activity Act) {
        this.Act = Act;
        createTables();

        if (!CheckData()) {
            InsertData();
        }


    }
    public ArrayList<Categoria> GetAllCategorias()
    {
        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        Cursor allrows = mydb.rawQuery("SELECT CATEGORIA,LETRA FROM TB_CATEGORIA", null);

        ArrayList<Categoria> List = new ArrayList<Categoria>();
        if (allrows.moveToFirst()) {
            do {

                Categoria NewI = new Categoria();
                NewI.Descricao = allrows.getString(0);
                NewI.Titulo = allrows.getString(1);
                List.add(NewI);
            }
            while (allrows.moveToNext());
        }
        mydb.close();
        return List;

    }
    private void InsertData() {
        String Insert = "INSERT INTO TB_CATEGORIA (CATEGORIA, LETRA)";
        Insert+="SELECT 'Procedimentos',''";
        Insert+="UNION ALL SELECT 'GERAL E INESPECÍFICO ','A'";
        Insert+="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','B'";
        Insert+="UNION ALL SELECT 'Digestivo','D'";
        Insert+="UNION ALL SELECT 'Olho','F'";
        Insert+="UNION ALL SELECT 'Ouvido','H'";
        Insert+="UNION ALL SELECT 'Circulatório','K'";
        Insert+="UNION ALL SELECT 'Músculo-esquelético','L'";
        Insert+="UNION ALL SELECT 'Neurológico','N'";
        Insert+="UNION ALL SELECT 'Psicológico','P'";
        Insert+="UNION ALL SELECT 'Respiratório','R'";
        Insert+="UNION ALL SELECT 'Pele','S'";
        Insert+="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','T'";
        Insert+="UNION ALL SELECT 'Urinário','U'";
        Insert+="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','W'";
        Insert+="UNION ALL SELECT 'Genital Feminino','X'";
        Insert+="UNION ALL SELECT 'Genital Masculino','Y'";
        Insert+="UNION ALL SELECT 'Problemas Sociais','Z'";

        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        mydb.execSQL(Insert);
        mydb.close();

        AddItens();



    }

    private void AddItens() {
        String Insert = "INSERT INTO TB_ITEM (CATEGORIA,GRUPO,COLORHEX,ITEM)";
        Insert +="SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-30 Exame médico/avaliação de saúde - completo'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-31 Exame médico/avaliação de saúde - parcial'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-32 Teste de sensibilidade'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-33 Exame microbiológico/imunológico'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-34 Análise de sangue'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-35 Análise de urina'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-36 Análise de fezes'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-37 Citologia esfoliativa/histologia'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-38 Outras análises laboratoriais NE'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-39 Teste de função física'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-40 Endoscopia diagnóstica'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-41 Radiologia diagnóstica'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-42 Eletrocardiograma'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-43 Outros procedimentos diagnósticos'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-44 Vacinação/medicação preventiva'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-45 Educação em saúde/aconselhamento/dieta'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-46 Consulta com profissional de APS'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-47 Consulta com especialista'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-48 Esclarecimento/discussão do motivo da consulta'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-49 Outros procedimentos preventivos'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-50 Medicação/prescrição/renovação/ injeção'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-51 Incisão/drenagem/aspiração/remoção'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-52 Excisão /biopsia/remoção/debridamento/cauterização'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-53 Cateterização/intubação'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-54 Reparação/sutura/gesso/prótese'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-55 Injeção local /infiltração'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-56 Ligadura/compressa /tamponamento'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-57 Medicina física/reabilitação'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-58 Aconselhamento/escuta terapêutica'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-59 Outros procedimentos terapêuticos/pequena cirurgia NE'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-60 Resultados de análises/procedimentos'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-61 Contra referência de outro prestador - resultado de exames/teste/ análise'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-62 Procedimento administrativo'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-63 Consulta de seguimento não especificada'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-64 Episódio / problema iniciado pelo prestador'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-65 Episódio / problema iniciado por outro NE'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-66 Referenciado a outro prestador /enfermeiro /assistente social/terapeuta'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-67 Referenciado para médico/especialista/clínica/hospital'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-68 Outras referenciações NE'";
        Insert +="UNION ALL SELECT 'Procedimentos','PROCEDIMENTOS','#D0CFCB','-69 Outro motivo de consulta NE'";

        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A01 Dor generalizada /múltipla'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A02 Arrepios/ calafrios'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A03 Febre'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A04 Debilidade/cansaço geral/fadiga'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A05 Sentir-se doente'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A06 Desmaio/síncope'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A07 Coma'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A08 Inchaço'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A09 Problemas de sudorese'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A10 Sangramento/Hemorragia NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A11 Dores torácicas NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A13 Receio/Medo do tratamento'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A16 Criança irritável'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A18 Preocupação com aparência'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A20 Pedido/discussão eutanásia'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A21 Fator de risco de malignidade'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A23 Fator de risco NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A25 Medo de morrer/medo da morte'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A26 Medo de câncer NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A27 Medo de outra doença NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A28 Limitação funcional/incapacidade NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','SINAIS/SINTOMAS','#D0E3B9','A29 Outros sinais/sintomas gerais'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A70 Tuberculose'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A71 Sarampo'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A72 Varicela'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A73 Malária'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A74 Rubéola'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A75 Mononucleose infecciosa'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A76 Outro exantema viral'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A77 Dengue e outras doenças virais NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','INFECÇÕES','#FAF3A0','A78 Hanseníase e outras doenças infecciosas NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','NEOPLASIAS','#D5EDF1','A79 Carcinomatose (localização primária desconhecida)'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A80 Lesão traumática/acidente NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A81 Politraumatismos/ferimentos múltiplos'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A82 Efeito secundário de lesão traumática'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A84 Intoxicação por medicamento'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A85 Efeito adverso de fármaco dose correta'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A86 Efeito tóxico de substância não medicinal'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A87 Complicações de tratamento médico'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A88 Efeito adverso de fator físico'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','TRAUMATISMOS','#F0A3A3','A89 Efeito da prótese'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','ANOMALIAS CONGÉNITAS','#B3ADD1','A90 Malformação congênita NE/múltiplas'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A91 Investigação com resultado anormal NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A92 Alergia/reação alérgica NE'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A93 Recém nascido prematuro'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A94 Morbidade perinatal, outra'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A95 Mortalidade perinatal'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A96 Morte'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A97 Sem doença'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A98 Medicina preventiva/manutenção da saúde'";
        Insert +="UNION ALL SELECT 'GERAL E INESPECÍFICO ','OUTROS DIAGNÓSTICOS','#D5A8C9','A99 Outras doenças gerais NE'";

        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','SINAIS/SINTOMAS','#D0E3B9','B02 Gânglio linfático aumentado/doloroso'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','SINAIS/SINTOMAS','#D0E3B9','B04 Sinais/sintomas sangue'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','SINAIS/SINTOMAS','#D0E3B9','B25 Medo de VIH/ HIV/SIDA/ AIDS'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','SINAIS/SINTOMAS','#D0E3B9','B26 Medo de câncer no sangue/linfático'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','SINAIS/SINTOMAS','#D0E3B9','B27 Medo de outras doenças do sangue /vasos/linfáticos'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','SINAIS/SINTOMAS','#D0E3B9','B28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','SINAIS/SINTOMAS','#D0E3B9','B29 Outros sinais/ sintomas do sangue/ sistema linfático/ baço NE'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','INFECÇÕES','#FAF3A0','B70 Linfadenite aguda'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','INFECÇÕES','#FAF3A0','B71 Linfadenite crónica NE'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','NEOPLASIAS','#D5EDF1','B72 Doença de Hodgkin/linfomas'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','NEOPLASIAS','#D5EDF1','B73 Leucemia'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','NEOPLASIAS','#D5EDF1','B74 Outra neoplasia maligna no sangue'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','NEOPLASIAS','#D5EDF1','B75 Neoplasia benigna NE'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','TRAUMATISMOS','#F0A3A3','B76 Rotura traumática do baço'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','TRAUMATISMOS','#F0A3A3','B77 Outras lesões traumáticas do sangue/linfa/baço'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','ANOMALIAS CONGÉNITAS','#B3ADD1','B78 Anemia hemolítica hereditária'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','ANOMALIAS CONGÉNITAS','#B3ADD1','B79 Outra malformação congénita do sangue/linfática'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B80 Anemia por deficiência ferro'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B81 Anemia perniciosa/deficiência de folatos'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B82 Outras anemias NE'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B83 Púrpura/defeitos de coagulação'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B84 Glóbulos brancos anormais'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B87 Esplenomegalia'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B90 Infecção por VIH/ HIV/SIDA/ AIDS'";
        Insert +="UNION ALL SELECT 'Sangue, sistema hematopoiético, linfático e baço','OUTROS DIAGNÓSTICOS','#D5A8C9','B99 Outra doença do sangue/linfáticos/baço'";


        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        mydb.execSQL(Insert);
        mydb.close();


        Insert = "INSERT INTO TB_ITEM (CATEGORIA,GRUPO,COLORHEX,ITEM)";
        Insert +="SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D01 Dor abdominal generalizada/cólicas'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D02 Dores abdominais, epigástricas'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D03 Azia/ Queimação'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D04 Dor anal/retal'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D05 Irritação perianal'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D06 Outras dores abdominais localizadas'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D07 Dispepsia/indigestão'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D08 Flatulência /gases/eructações'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D09 Náusea'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D10 Vomito'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D11 Diarreia'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D12 Obstipação'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D13 Icterícia'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D14 Hematêmese/vómito sangue'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D15 Melena'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D16 Hemorragia retal'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D17 Incontinência fecal'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D18 Alterações nas fezes/mov. intestinais'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D19 Sinais/sintomas dos dentes/gengivas'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D20 Sinais/sintomas da boca/língua/lábios'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D21 Problemas de deglutição'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D23 Hepatomegalia'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D24 Massa abdominal NE'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D25 Distensão abdominal'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D26 Medo de câncer no aparelho digestivo'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D27 Medo de outras doenças aparelho digestivo'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Digestivo','SINAIS/SINTOMAS','#D0E3B9','D29 Outros sinais/sintomas digestivos'";
        Insert +="UNION ALL SELECT 'Digestivo','INFECÇÕES','#FAF3A0','D70 Infecção gastrointestinal'";
        Insert +="UNION ALL SELECT 'Digestivo','INFECÇÕES','#FAF3A0','D71 Caxumba/parotidite epidêmica'";
        Insert +="UNION ALL SELECT 'Digestivo','INFECÇÕES','#FAF3A0','D72 Hepatite viral'";
        Insert +="UNION ALL SELECT 'Digestivo','INFECÇÕES','#FAF3A0','D73 Gastroenterite, presumível infecção'";
        Insert +="UNION ALL SELECT 'Digestivo','NEOPLASIAS','#D5EDF1','D74 Neoplasia maligna do estômago'";
        Insert +="UNION ALL SELECT 'Digestivo','NEOPLASIAS','#D5EDF1','D75 Neoplasia maligna do cólon/reto'";
        Insert +="UNION ALL SELECT 'Digestivo','NEOPLASIAS','#D5EDF1','D76 Neoplasia maligna do pâncreas'";
        Insert +="UNION ALL SELECT 'Digestivo','NEOPLASIAS','#D5EDF1','D77 Neoplasia maligna do aparelho digestivo NE'";
        Insert +="UNION ALL SELECT 'Digestivo','NEOPLASIAS','#D5EDF1','D78 Neoplasia benigna do aparelho digestivo/incerta'";
        Insert +="UNION ALL SELECT 'Digestivo','TRAUMATISMOS','#F0A3A3','D79 Corpo estranho no aparelho digestivo'";
        Insert +="UNION ALL SELECT 'Digestivo','TRAUMATISMOS','#F0A3A3','D80 Outras lesões traumáticas'";
        Insert +="UNION ALL SELECT 'Digestivo','ANOMALIAS CONGÉNITAS','#B3ADD1','D81 Malformações congênitas do aparelho digestivo'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D82 Doença dos dentes/gengivas'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D83 Doença da boca/língua/lábios'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D84 Doença do esôfago'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D85 Úlcera do duodeno'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D86 Úlcera péptica, outra'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D87 Alterações funcionais estômago'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D88 Apendicite'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D89 Hérnia inguinal'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D90 Hérnia de hiato /diafragmática'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D91 Hérnia abdominal, outras'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D92 Doença diverticular intestinal'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D93 Síndrome do cólon irritável'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D94 Enterite crónica / colite ulcerosa'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D95 Fissura anal / abcesso perianal'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D96 Lombrigas /outros parasitas'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D97 Doenças do fígado /NE'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D98 Colecistite, colelitiase'";
        Insert +="UNION ALL SELECT 'Digestivo','OUTROS DIAGNÓSTICOS','#D5A8C9','D99 Outra doença do aparelho digestivo'";

        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F01 Dor no olho'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F02 Olho vermelho'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F03 Secreção ocular'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F04 Moscas volantes/pontos luminosos/escotomas/ manchas'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F05 Outras perturbações visuais'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F13 Sensações oculares anormais'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F14 Movimentos oculares anormais'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F15 Aparência anormal nos olhos'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F16 Sinais/sintomas das pálpebras'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F17 Sinais/sintomas relacionados a óculos'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F18 Sinais/sintomas relacionados a lentes de contato'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F27 Medo de doença ocular'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Olho','SINAIS/SINTOMAS','#D0E3B9','F29 Outros sinais/sintomas oculares'";
        Insert +="UNION ALL SELECT 'Olho','INFECÇÕES','#FAF3A0','F70 Conjuntivite infecciosa'";
        Insert +="UNION ALL SELECT 'Olho','INFECÇÕES','#FAF3A0','F71 Conjuntivite alérgica'";
        Insert +="UNION ALL SELECT 'Olho','INFECÇÕES','#FAF3A0','F72 Blefarite/hordéolo/calázio'";
        Insert +="UNION ALL SELECT 'Olho','INFECÇÕES','#FAF3A0','F73 Outras infecções/inflamações oculares'";
        Insert +="UNION ALL SELECT 'Olho','NEOPLASIAS','#D5EDF1','F74 Neoplasia do olho/anexos'";
        Insert +="UNION ALL SELECT 'Olho','TRAUMATISMOS','#F0A3A3','F75 Contusão/hemorragia ocular'";
        Insert +="UNION ALL SELECT 'Olho','TRAUMATISMOS','#F0A3A3','F76 Corpo estranho ocular'";
        Insert +="UNION ALL SELECT 'Olho','TRAUMATISMOS','#F0A3A3','F79 Outras lesões traumáticas oculares'";
        Insert +="UNION ALL SELECT 'Olho','ANOMALIAS CONGÉNITAS','#B3ADD1','F80 Obstrução canal lacrimal da criança'";
        Insert +="UNION ALL SELECT 'Olho','ANOMALIAS CONGÉNITAS','#B3ADD1','F81 Outras malformações congênitas do olho'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F82 Descolamento da retina'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F83 Retinopatia'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F84 Degeneração macular'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F85 Ulcera da córnea'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F86 Tracoma'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F91 Erro de refração'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F92 Catarata'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F93 Glaucoma'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F94 Cegueira'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F95 Estrabismo'";
        Insert +="UNION ALL SELECT 'Olho','OUTROS DIAGNÓSTICOS','#D5A8C9','F99 Outra doenças oculares/anexos'";

        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H01 Dor de ouvidos'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H02 Problemas de audição'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H03 Acufeno, zumbidos, ruído, assobios'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H04 Secreção no ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H05 Hemorragia no ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H13 Sensação de ouvido tapado'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H15 Preocupação com a aparência das orelhas'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H27 Medo de doença do ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Ouvido','SINAIS/SINTOMAS','#D0E3B9','H29 Outros sinais/sintomas ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','INFECÇÕES','#FAF3A0','H70 Otite externa'";
        Insert +="UNION ALL SELECT 'Ouvido','INFECÇÕES','#FAF3A0','H71 Otite media aguda/miringite'";
        Insert +="UNION ALL SELECT 'Ouvido','INFECÇÕES','#FAF3A0','H72 Otite média serosa'";
        Insert +="UNION ALL SELECT 'Ouvido','INFECÇÕES','#FAF3A0','H73 Infecção da Trompa de Eustáquio'";
        Insert +="UNION ALL SELECT 'Ouvido','INFECÇÕES','#FAF3A0','H74 Otite media crónica'";
        Insert +="UNION ALL SELECT 'Ouvido','NEOPLASIAS','#D5EDF1','H75 Neoplasia do ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','TRAUMATISMOS','#F0A3A3','H76 Corpo estranho do ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','TRAUMATISMOS','#F0A3A3','H77 Perfuração do tímpano'";
        Insert +="UNION ALL SELECT 'Ouvido','TRAUMATISMOS','#F0A3A3','H78 Traumatismo superficial do ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','TRAUMATISMOS','#F0A3A3','H79 Outros traumatismos do ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','ANOMALIAS CONGÉNITAS','#B3ADD1','H80 Malformações congênitas do ouvido'";
        Insert +="UNION ALL SELECT 'Ouvido','OUTROS DIAGNÓSTICOS','#D5A8C9','H81 Cerúmen no ouvido em excesso'";
        Insert +="UNION ALL SELECT 'Ouvido','OUTROS DIAGNÓSTICOS','#D5A8C9','H82 Síndrome vertiginosa'";
        Insert +="UNION ALL SELECT 'Ouvido','OUTROS DIAGNÓSTICOS','#D5A8C9','H83 Otoesclerose'";
        Insert +="UNION ALL SELECT 'Ouvido','OUTROS DIAGNÓSTICOS','#D5A8C9','H84 Presbiacusia'";
        Insert +="UNION ALL SELECT 'Ouvido','OUTROS DIAGNÓSTICOS','#D5A8C9','H85 Lesão acústica'";
        Insert +="UNION ALL SELECT 'Ouvido','OUTROS DIAGNÓSTICOS','#D5A8C9','H86 Surdez'";
        Insert +="UNION ALL SELECT 'Ouvido','OUTROS DIAGNÓSTICOS','#D5A8C9','H99 Outra doença do ouvido/mastóide'";

        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K01 Dor atribuída ao coração'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K02 Sensação de pressão/aperto atribuída ao coração'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K03 Dores atribuídas ao aparelho circulatório NE'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K04 Palpitações/percepção dos batimentos cardíacos'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K05 Outras irregularidades dos batimentos cardíacos'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K06 Veias proeminentes'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K07 Tornozelos inchados/edema'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K22 Fator de risco para doença cardiovascular'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K24 Medo de doença cardíaca'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K25 Medo de hipertensão'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K27 Medo de outra doença cardiovascular'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Circulatório','SINAIS/SINTOMAS','#D0E3B9','K29 Outros sinais/sintomas cardiovasculares'";
        Insert +="UNION ALL SELECT 'Circulatório','INFECÇÕES','#FAF3A0','K70 Doença infecciosa do aparelho circulatório'";
        Insert +="UNION ALL SELECT 'Circulatório','INFECÇÕES','#FAF3A0','K71 Febre reumática/cardiopatia'";
        Insert +="UNION ALL SELECT 'Circulatório','NEOPLASIAS','#D5EDF1','K72 Neoplasia do aparelho circulatório'";
        Insert +="UNION ALL SELECT 'Circulatório','TRAUMATISMOS','#F0A3A3','K73 Malformações congênitas do aparelho circulatório'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K74 Doença cardíaca isquémica com angina'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K75 Infarto ou Enfarte agudo miocárdio'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K76 Doença cardíaca isquémica sem angina'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K77 Insuficiência cardíaca'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K78 Fibrilação/flutter auricular/ atrial'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K79 Taquicardia Paroxística'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K80 Arritmia cardíaca NE'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K81 Sopro cardíaco/arterial NE'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K82 Doença cardiopulmonar'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K83 Doença valvular cardíaca NE'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K84 Outras doenças cardíacas'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K85 Pressão arterial elevada'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K86 Hipertensão sem complicações'";
        Insert +="UNION ALL SELECT 'Circulatório','ANOMALIAS CONGÉNITAS','#B3ADD1','K87 Hipertensão com complicações'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K88 Hipotensão postural'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K89 Isquêmia/ acidente cerebral transitória(o)'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K90 Trombose/acidente vascular cerebral'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K91 Doença vascular cerebral'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K92 Aterosclerose/doença vascular periférica'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K93 Embolia pulmonar'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K94 Flebite/tromboflebite'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K95 Veias varicosas da perna'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K96 Hemorróidas'";
        Insert +="UNION ALL SELECT 'Circulatório','OUTROS DIAGNÓSTICOS','#D5A8C9','K99 Outras doenças do aparelho circulatório'";


        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        mydb.execSQL(Insert);
        mydb.close();

        Insert = "INSERT INTO TB_ITEM (CATEGORIA,GRUPO,COLORHEX,ITEM)";
        Insert +="SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L01 Sinais/sintomas do pescoço'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L02 Sinais/sintomas da região dorsal'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L03 Sinais/sintomas da região lombar'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L04 Sinais/sintomas do tórax'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L05 Sinais/sintomas da axila'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L07 Sinais/sintomas da mandíbula'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L08 Sinais/sintomas dos ombros'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L09 Sinais/sintomas dos braços'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L10 Sinais/sintomas dos cotovelos'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L11 Sinais/sintomas dos punhos'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L12 Sinais/sintomas das mãos e dedos'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L13 Sinais/sintomas do quadril'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L14 Sinais/sintomas da coxa/perna'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L15 Sinais/sintomas do joelho'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L16 Sinais/sintomas do tornozelo'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L17 Sinais/sintomas do pé/dedos pé'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L18 Dores musculares'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L19 Sinais/sintomas musculares NE'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L20 Sinais/sintomas das articulações NE'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L26 Medo de câncer no aparelho músculoesquelético'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L27 Medo de doença no aparelho músculo-esquelético, outro'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','SINAIS/SINTOMAS','#D0E3B9','L29 Outros sinais/sintomas do aparelho músculo-esquelético'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','INFECÇÕES','#FAF3A0','L70 Infecções do aparelho músculo-esquelético'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','NEOPLASIAS','#D5EDF1','L71 Neoplasia maligna do aparelho músculoesquelético'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L72 Fratura: rádio/cúbito'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L73 Fratura: tíbia/perônio/ fíbula'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L74 Fratura: osso da mão/pé'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L75 Fratura: fémur'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L76 Outras fraturas'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L77 Entorses e distensões do tornozelo'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L78 Entorses e distensões do joelho'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L79 Entorses e distensões das articulações NE'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L80 Luxação/subluxação'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L81 Traumatismos do aparelho musculoesquelético NE'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','ANOMALIAS CONGÉNITAS','#B3ADD1','L82 Malformações congênitas do aparelho músculo-esquelético'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L83 Doenças ou síndromes da coluna cervical'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L84 Doenças ou síndromes da coluna sem irradiação de dor'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L85 Deformação adquirida da coluna'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L86 Síndrome vertebral com irradiação dor'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L87 Bursite/tendinite/sinovite NE'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L88 Artrite reumatóide/seropositiva'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L89 Osteoartrose do quadril'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L90 Osteoartrose do joelho'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L91 Outras osteoartroses'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L92 Síndrome do ombro doloroso'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L93 Cotovelo de tenista'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L94 Osteocondrose'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L95 Osteoporose'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','TRAUMATISMOS','#F0A3A3','L96 Lesão interna aguda do joelho'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','NEOPLASIAS','#D5EDF1','L97 Neoplasia benigna/incertas'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L98 Malformação adquirida de um membro'";
        Insert +="UNION ALL SELECT 'Músculo-esquelético','OUTROS DIAGNÓSTICOS','#D5A8C9','L99 Outra doença do aparelho músculo-esquelético'";

        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N01 Cefaléia'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N03 Dores da face'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N04 Síndrome das pernas inquietas'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N05 Formigamento/ parestesia nos dedos das mãos/pés'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N06 Outras alterações da sensibilidade'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N07 Convulsões/ataques'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N08 Movimentos involuntários anormais'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N16 Alterações do olfato/gosto'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N17 Vertigens/tonturas'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N18 Paralisia/fraqueza'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N19 Perturbações da fala'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N26 Medo de câncer do sistema neurológico'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N27 Medo de outras doenças neurológicas'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Neurológico','SINAIS/SINTOMAS','#D0E3B9','N29 Sinais/sintomas do sistema neurológico, outros'";
        Insert +="UNION ALL SELECT 'Neurológico','INFECÇÕES','#FAF3A0','N70 Poliomielite'";
        Insert +="UNION ALL SELECT 'Neurológico','INFECÇÕES','#FAF3A0','N71 Meningite/encefalite'";
        Insert +="UNION ALL SELECT 'Neurológico','INFECÇÕES','#FAF3A0','N72 Tétano'";
        Insert +="UNION ALL SELECT 'Neurológico','INFECÇÕES','#FAF3A0','N73 Outra infecção neurológica'";
        Insert +="UNION ALL SELECT 'Neurológico','NEOPLASIAS','#D5EDF1','N74 Neoplasia maligna do sistema neurológico'";
        Insert +="UNION ALL SELECT 'Neurológico','NEOPLASIAS','#D5EDF1','N75 Neoplasia benigna do sistema neurológico'";
        Insert +="UNION ALL SELECT 'Neurológico','NEOPLASIAS','#D5EDF1','N76 Neoplasia do sistema neurológico de natureza incerta'";
        Insert +="UNION ALL SELECT 'Neurológico','TRAUMATISMOS','#F0A3A3','N79 Concussão'";
        Insert +="UNION ALL SELECT 'Neurológico','TRAUMATISMOS','#F0A3A3','N80 Outras lesões cranianas'";
        Insert +="UNION ALL SELECT 'Neurológico','TRAUMATISMOS','#F0A3A3','N81 Outra lesão do sistema neurológico'";
        Insert +="UNION ALL SELECT 'Neurológico','ANOMALIAS CONGÉNITAS','#B3ADD1','N85 Malformações congênitas'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N86 Esclerose múltipla'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N87 Parkinsonismo'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N88 Epilepsia'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N89 Enxaqueca'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N90 Cefaléia de cluster'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N91 Paralisia facial/paralisia de Bell'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N92 Nevralgia do trigémio'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N93 Síndrome do túnel do carpo/ Síndrome do canal cárpico'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N94 Neurite/ Nevrite/neuropatia periférica'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N95 Cefaléia tensional'";
        Insert +="UNION ALL SELECT 'Neurológico','OUTROS DIAGNÓSTICOS','#D5A8C9','N99 Outras doenças do sistema neurológico'";

        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P01 Sensação de ansiedade/nervosismo/tensão'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P02 Reação aguda ao estresse'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P03 Tristeza/ Sensação de depressão'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P04 Sentir/comportar-se de forma irritável/zangada'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P05 Sensação/comportamento senil'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P06 Perturbação do sono'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P07 Diminuição do desejo sexual'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P08 Diminuição da satisfação sexual'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P09 Preocupação com a preferência sexual'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P10 Gaguejar/balbuciar/tiques'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P11 Problemas de alimentação da criança'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P12 Molhar a cama/enurese'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P13 Encoprese/outros problemas de incontinência fecal'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P15 Abuso crônico de álcool'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P16 Abuso agudo de álcool'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P17 Abuso do tabaco'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P18 Abuso de medicação'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P19 Abuso de drogas'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P20 Alterações da memória'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P22 Sinais/sintomas relacionados ao comportamento da criança'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P23 Sinais/sintomas relacionados ao comportamento do adolescente'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P24 Dificuldades especificas de aprendizagem'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P25 Problemas da fase de vida de adulto'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P27 Medo de perturbações mentais'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Psicológico','SINAIS/SINTOMAS','#D0E3B9','P29 Sinais/sintomas psicológicos, outros'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P70 Demência'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P71 Outras psicoses orgânicas NE'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P72 Esquizofrenia'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P73 Psicose afetiva'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P74 Distúrbio ansioso/estado de ansiedade'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P75 Somatização'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P76 Perturbações depressivas'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P77 Suicídio/tentativa de suicídio'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P78 Neurastenia'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P79 Fobia/perturbação compulsiva'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P80 Perturbações de personalidade'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P81 Perturbação hipercinética'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P82 Estresse pós traumático'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P85 Retardo/ Atraso mental'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P86 Anorexia nervosa, bulimia'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P98 Outras psicoses NE'";
        Insert +="UNION ALL SELECT 'Psicológico','OUTROS DIAGNÓSTICOS','#D5A8C9','P99 Outras perturbações psicológicas'";

        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R01 Dor atribuída ao aparelho respiratório'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R02 Dificuldade respiratória, dispneia'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R03 Respiração ruidosa'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R04 Outros problemas respiratórios'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R05 Tosse'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R06 Hemorragia nasal/epistaxe'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R07 Espirro/congestão nasal'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R08 Outros sinais/sintomas nasais'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R09 Sinais/sintomas dos seios paranasais'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R21 Sinais/sintomas da garganta'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R23 Sinais/sintomas da voz'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R24 Hemoptise'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R25 Expectoração/mucosidade anormal'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R26 Medo de câncer do aparelho respiratório'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R27 Medo de outras doenças respiratórias'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Respiratório','SINAIS/SINTOMAS','#D0E3B9','R29 Sinais/sintomas do aparelho respiratório, outros'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R71 Tosse convulsa/ pertussis'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R72 Infecção estreptocócica da orofaringe'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R73 Abcesso/furúnculo no nariz'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R74 Infecção aguda do aparelho respiratório superior (IVAS)'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R75 Sinusite crónica/aguda'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R76 Amigdalite aguda'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R77 Laringite/traqueíte aguda'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R78 Bronquite/bronquiolite aguda'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R79 Bronquite crónica'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R80 Gripe'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R81 Pneumonia'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R82 Pleurite/derrame pleural'";
        Insert +="UNION ALL SELECT 'Respiratório','INFECÇÕES','#FAF3A0','R83 Outra infecção respiratória'";
        Insert +="UNION ALL SELECT 'Respiratório','NEOPLASIAS','#D5EDF1','R84 Neoplasia maligna dos brônquios/pulmão'";
        Insert +="UNION ALL SELECT 'Respiratório','NEOPLASIAS','#D5EDF1','R85 Outra neoplasia respiratória maligna'";
        Insert +="UNION ALL SELECT 'Respiratório','NEOPLASIAS','#D5EDF1','R86 Neoplasia benigna respiratória'";
        Insert +="UNION ALL SELECT 'Respiratório','TRAUMATISMOS','#F0A3A3','R87 Corpo estranho nariz/laringe/brônquios'";
        Insert +="UNION ALL SELECT 'Respiratório','TRAUMATISMOS','#F0A3A3','R88 Outra lesão respiratória'";
        Insert +="UNION ALL SELECT 'Respiratório','ANOMALIAS CONGÉNITAS','#B3ADD1','R89 Malformação congénita do aparelho respiratório'";
        Insert +="UNION ALL SELECT 'Respiratório','OUTROS DIAGNÓSTICOS','#D5A8C9','R90 Hipertrofia das amígdalas/adenóides'";
        Insert +="UNION ALL SELECT 'Respiratório','NEOPLASIAS','#D5EDF1','R92 Neoplasia respiratória NE'";
        Insert +="UNION ALL SELECT 'Respiratório','OUTROS DIAGNÓSTICOS','#D5A8C9','R95 Doença pulmonar obstrutiva crónica'";
        Insert +="UNION ALL SELECT 'Respiratório','OUTROS DIAGNÓSTICOS','#D5A8C9','R96 Asma'";
        Insert +="UNION ALL SELECT 'Respiratório','OUTROS DIAGNÓSTICOS','#D5A8C9','R97 Rinite alérgica'";
        Insert +="UNION ALL SELECT 'Respiratório','OUTROS DIAGNÓSTICOS','#D5A8C9','R98 Síndrome de hiperventilação'";
        Insert +="UNION ALL SELECT 'Respiratório','OUTROS DIAGNÓSTICOS','#D5A8C9','R99 Outras doenças respiratórias'";

        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S01 Dor/sensibilidade dolorosa da pele'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S02 Prurido'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S03 Verrugas'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S04 Tumor/inchaço localizado'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S05 Tumores/inchaços generalizados'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S06 Erupção cutânea localizada'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S07 Erupção cutânea generalizada'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S08 Alterações da cor da pele'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S09 Infecção dos dedos das mãos/pés'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S10 Furúnculo/carbúnculo'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S11 Infecção pós-traumática da pele'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S12 Picada ou mordedura de inseto'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S13 Mordedura animal/humana'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S14 Queimadura/escaldão'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S15 Corpo estranho na pele'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S16 Traumatismo/contusão'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S17 Abrasão/arranhão/bolhas'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S18 Laceração/corte'";
        Insert +="UNION ALL SELECT 'Pele','TRAUMATISMOS','#F0A3A3','S19 Outra lesão cutânea'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S20 Calos/calosidades'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S21 Sinais/sintomas da textura da pele'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S22 Sinais/sintomas das unhas'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S23 Queda de cabelo/calvície'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S24 Sinais/sintomas do cabelo/couro cabeludo'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S26 Medo de câncer de pele'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S27 Medo de outra doença da pele'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Pele','SINAIS/SINTOMAS','#D0E3B9','S29 Sinais/sintomas da pele, outros'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S70 Herpes zoster'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S71 Herpes simples'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S72 Escabiose/outras acaríases'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S73 Pediculose/outras infecções da pele'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S74 Dermatofitose'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S75 Monilíase oral/candidíase na pele'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S76 Outras infecções da pele'";
        Insert +="UNION ALL SELECT 'Pele','NEOPLASIAS','#D5EDF1','S77 Neoplasias malignas da pele'";
        Insert +="UNION ALL SELECT 'Pele','NEOPLASIAS','#D5EDF1','S78 Lipoma'";
        Insert +="UNION ALL SELECT 'Pele','NEOPLASIAS','#D5EDF1','S79 Neoplasia cutânea benigna/incerta'";
        Insert +="UNION ALL SELECT 'Pele','NEOPLASIAS','#D5EDF1','S80 Ceratose/ Queratose solar/queimadura solar'";
        Insert +="UNION ALL SELECT 'Pele','ANOMALIAS CONGÉNITAS','#B3ADD1','S81 Hemangioma/linfangioma'";
        Insert +="UNION ALL SELECT 'Pele','ANOMALIAS CONGÉNITAS','#B3ADD1','S82 Nevos/sinais da pele'";
        Insert +="UNION ALL SELECT 'Pele','ANOMALIAS CONGÉNITAS','#B3ADD1','S83 Lesões da pele congênitas, outras'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S84 Impetigo'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S85 Cisto pilonidal/fistula'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S86 Dermatite seborreica'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S87 Dermatite/eczema atópico'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S88 Dermatite de contato/alérgica'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S89 Dermatite das fraldas'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S90 Pitiríase rosada'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S91 Psoríase'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S92 Doença das glândulas sudoríparas'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S93 Cisto sebáceo'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S94 Unha encravada'";
        Insert +="UNION ALL SELECT 'Pele','INFECÇÕES','#FAF3A0','S95 Molusco contagioso'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S96 Acne'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S97 Úlcera crónica da pele'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S98 Urticária'";
        Insert +="UNION ALL SELECT 'Pele','OUTROS DIAGNÓSTICOS','#D5A8C9','S99 Outras doenças da pele'";

        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        mydb.execSQL(Insert);
        mydb.close();

        Insert = "INSERT INTO TB_ITEM (CATEGORIA,GRUPO,COLORHEX,ITEM)";
        Insert +="SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T01 Sede excessiva'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T02 Apetite excessivo'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T03 Perda de apetite'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T04 Problemas alimentares de lactente/criança'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T05 Problemas alimentares do adulto'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T07 Aumento de peso'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T08 Perda de peso'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T10 Atraso do crescimento'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T11 Desidratação'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T26 Medo de câncer do sistema endócrino'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T27 Medo de outra doença endócrina/metabólica'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','SINAIS/SINTOMAS','#D0E3B9','T29 Sinais/sintomas endocrinológicos/metabolicos/nutricionais, outros'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','INFECÇÕES','#FAF3A0','T70 Infecção endócrina'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','NEOPLASIAS','#D5EDF1','T71 Neoplasia maligna da tiróide'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','NEOPLASIAS','#D5EDF1','T72 Neoplasia benigna da tiróide'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','NEOPLASIAS','#D5EDF1','T73 Outra neoplasia endócrina NE'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','ANOMALIAS CONGÉNITAS','#B3ADD1','T78 Cisto do canal tiroglosso'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','ANOMALIAS CONGÉNITAS','#B3ADD1','T80 Malformação congénita endócrina/metabólica'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T81 Bócio'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T82 Obesidade'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T83 Excesso de peso'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T85 Hipertiroidismo/tireotoxicose'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T86 Hipotiroidismo/mixedema'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T87 Hipoglicemia'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T89 Diabetes insulino-dependente'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T90 Diabetes não insulino-dependente'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T91 Deficiência vitamínica/nutricional'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T92 Gota'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T93 Alteração no metabolismo dos lípidos'";
        Insert +="UNION ALL SELECT 'Endócrino/Metabólico e Nutricional','TRAUMATISMOS','#F0A3A3','T99 Outras doenças end'";

        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U01 Disúria/micção dolorosa'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U02 Micção frequente/urgência urinária/ polaciúria'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U04 Incontinência urinária'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U05 Outros problemas com a micção'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U06 Hematúria'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U07 Outros sinais/sintomas urinários'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U08 Retenção urinária'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U13 Sinais/sintomas da bexiga, outros'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U14 Sinais/sintomas dos rins'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U26 Medo de câncer no aparelho urinário'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U27 Medo de outra doença urinária'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Urinário','SINAIS/SINTOMAS','#D0E3B9','U29 Sinais/sintomas aparelho urinário, outros'";
        Insert +="UNION ALL SELECT 'Urinário','INFECÇÕES','#FAF3A0','U70 Pielonefrite'";
        Insert +="UNION ALL SELECT 'Urinário','INFECÇÕES','#FAF3A0','U71 Cistite/outra infecção urinária'";
        Insert +="UNION ALL SELECT 'Urinário','INFECÇÕES','#FAF3A0','U72 Uretrite'";
        Insert +="UNION ALL SELECT 'Urinário','NEOPLASIAS','#D5EDF1','U75 Neoplasia maligna do rim'";
        Insert +="UNION ALL SELECT 'Urinário','NEOPLASIAS','#D5EDF1','U76 Neoplasia benigna do rim'";
        Insert +="UNION ALL SELECT 'Urinário','NEOPLASIAS','#D5EDF1','U77 Neoplasia maligna do aparelho urinário, outra'";
        Insert +="UNION ALL SELECT 'Urinário','NEOPLASIAS','#D5EDF1','U78 Neoplasia benigna do aparelho urinário'";
        Insert +="UNION ALL SELECT 'Urinário','NEOPLASIAS','#D5EDF1','U79 Neoplasia do aparelho urinário NE'";
        Insert +="UNION ALL SELECT 'Urinário','TRAUMATISMOS','#F0A3A3','U80 Lesões traumáticas do aparelho urinário'";
        Insert +="UNION ALL SELECT 'Urinário','ANOMALIAS CONGÉNITAS','#B3ADD1','U85 Malformação congénita do aparelho urinário'";
        Insert +="UNION ALL SELECT 'Urinário','OUTROS DIAGNÓSTICOS','#D5A8C9','U88 Glomerulonefrite/ sindrome nefrótica'";
        Insert +="UNION ALL SELECT 'Urinário','OUTROS DIAGNÓSTICOS','#D5A8C9','U90 Albuminúria/proteinúria ortostática'";
        Insert +="UNION ALL SELECT 'Urinário','OUTROS DIAGNÓSTICOS','#D5A8C9','U95 Cálculo urinário'";
        Insert +="UNION ALL SELECT 'Urinário','OUTROS DIAGNÓSTICOS','#D5A8C9','U98 Análise de urina anormal NE'";
        Insert +="UNION ALL SELECT 'Urinário','OUTROS DIAGNÓSTICOS','#D5A8C9','U99 Outras doenças urinárias'";

        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W01 Questão sobre gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W02 Medo de estar grávida'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W03 Hemorragia antes do parto'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W05 Vómitos/náuseas durante a gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W10 Contracepção pós-coital'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W11 Contracepção oral'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W12 Contracepção intra-uterina/ Dispositivo Intrauterino/ DIU'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W13 Esterilização'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W14 Contracepção/outros'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W15 Infertilidade/subfertildade'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W17 Hemorragia pós-parto'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W18 Sinais/sintomas pós-parto'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W19 Sinais/sintomas da mama/lactação'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W21 Preocupação com a imagem corporal na gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W27 Medo de complicações na gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','SINAIS/SINTOMAS','#D0E3B9','W29 Sinais/sintomas da gravidez, outros'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','INFECÇÕES','#FAF3A0','W70 Sepsis/infecção puerperal'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','INFECÇÕES','#FAF3A0','W71 Infecções que complicam a gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','NEOPLASIAS','#D5EDF1','W72 Neoplasia maligna relacionada com gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','NEOPLASIAS','#D5EDF1','W73 Neoplasia benigna/incerta relacionada com a gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','TRAUMATISMOS','#F0A3A3','W75 Lesões traumáticas que complicam a gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','ANOMALIAS CONGÉNITAS','#B3ADD1','W76 Malformação congénita que complica a gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W78 Gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W79 Gravidez não desejada'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W80 Gravidez ectópica'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W81 Toxemia gravídica/ DHEG'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W82 Aborto espontâneo'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W83 Aborto provocado de alto risco'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W84 Gravidez'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W85 Diabetes gestacional'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W90 Parto sem complicações de nascido vivo'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W91 Parto sem complicações de natimorto'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W92 Parto com complicações de nascido vivo'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W93 Parto com complicações de natimorto'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W94 Mastite puerperal'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W95 Outros problemas da mama durante gravidez/puerpério'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W96 Outras complicações do puerpério'";
        Insert +="UNION ALL SELECT 'Gravidez, Parto e Planejamento Familiar','OUTROS DIAGNÓSTICOS','#D5A8C9','W99 Outros problemas da gravidez/parto'";

        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X01 Dor genital'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X02 Dores menstruais'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X03 Dores intermenstruais'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X04 Relação sexual dolorosa na mulher'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X05 Menstruação escassa/ausente'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X06 Menstruação excessiva'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X07 Menstruação irregular/frequente'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X08 Hemorragia intermenstrual'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X09 Sinais/sintomas pré-menstruais'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X10 Desejo de alterar a data menstruação'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X11 Sinais/sintomas da menopausa/ climatério'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X12 Hemorragia pós-menopausa'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X13 Hemorragia pós-coital'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X14 Secreção vaginal'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X15 Sinais/sintomas da vagina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X16 Sinais/sintomas da vulva'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X17 Sinais/sintomas da pélvis feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X18 Dor na mama feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X19 Tumor ou nódulo na mama feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X20 Sinais/sintomas do mamilo da mulher'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X21 Sinais/sintomas da mama feminina, outros'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X22 Preocupação com a aparência da mama feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X23 Medo de doença de transmissão sexual'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X24 Medo de disfunção sexual'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X25 Medo de câncer genital'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X26 Medo de câncer na mama'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X27 Medo de outra doença genital/mama'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Genital Feminino','SINAIS/SINTOMAS','#D0E3B9','X29 Sinais/sintomas do aparelho genital feminino, outra'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X70 Sífilis feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X71 Gonorréia feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X72 Candidíase genital feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X73 Tricomoníase genital feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X74 Doença inflamatória pélvica'";
        Insert +="UNION ALL SELECT 'Genital Feminino','NEOPLASIAS','#D5EDF1','X75 Neoplasia maligna do colo'";
        Insert +="UNION ALL SELECT 'Genital Feminino','NEOPLASIAS','#D5EDF1','X76 Neoplasia maligna da mama feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','NEOPLASIAS','#D5EDF1','X77 Neoplasia maligna genital feminina, outra'";
        Insert +="UNION ALL SELECT 'Genital Feminino','NEOPLASIAS','#D5EDF1','X78 Fibromioma uterino'";
        Insert +="UNION ALL SELECT 'Genital Feminino','NEOPLASIAS','#D5EDF1','X79 Neoplasia benigna da mama feminina/ fibroadenoma'";
        Insert +="UNION ALL SELECT 'Genital Feminino','NEOPLASIAS','#D5EDF1','X80 Neoplasia benigna genital'";
        Insert +="UNION ALL SELECT 'Genital Feminino','NEOPLASIAS','#D5EDF1','X81 Neoplasia genital feminina, outra/NE'";
        Insert +="UNION ALL SELECT 'Genital Feminino','TRAUMATISMOS','#F0A3A3','X82 Lesão traumática genital feminina'";
        Insert +="UNION ALL SELECT 'Genital Feminino','ANOMALIAS CONGÉNITAS','#B3ADD1','X83 Malformações congênitas genitais'";
        Insert +="UNION ALL SELECT 'Genital Feminino','OUTROS DIAGNÓSTICOS','#D5A8C9','X84 Vaginite/vulvite NE'";
        Insert +="UNION ALL SELECT 'Genital Feminino','OUTROS DIAGNÓSTICOS','#D5A8C9','X85 Doença do colo NE'";
        Insert +="UNION ALL SELECT 'Genital Feminino','OUTROS DIAGNÓSTICOS','#D5A8C9','X86 Esfregaço de Papanicolau/colpocitologia oncótica anormal'";
        Insert +="UNION ALL SELECT 'Genital Feminino','OUTROS DIAGNÓSTICOS','#D5A8C9','X87 Prolapso utero-vaginal'";
        Insert +="UNION ALL SELECT 'Genital Feminino','OUTROS DIAGNÓSTICOS','#D5A8C9','X88 Doença fibrocística da mama'";
        Insert +="UNION ALL SELECT 'Genital Feminino','OUTROS DIAGNÓSTICOS','#D5A8C9','X89 Síndrome da tensão pré-menstrual'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X90 Herpes genital feminino'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X91 Condiloma acuminado feminino'";
        Insert +="UNION ALL SELECT 'Genital Feminino','INFECÇÕES','#FAF3A0','X92 Infecção por clamídia'";
        Insert +="UNION ALL SELECT 'Genital Feminino','OUTROS DIAGNÓSTICOS','#D5A8C9','X99 Doença genital feminina, outra'";

        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y01 Dor no pênis'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y02 Dor no escroto/testículos'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y03 Secreção uretral'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y04 Sinais/sintomas do pênis, outros'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y05 Sinais/sintomas do escroto/ testículos, outros'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y06 Sinais/sintomas da próstata'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y07 Impotência NE'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y08 Sinais/sintomas da função sexual masculina, outros'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y10 Infertilidade/subfertildade masculina'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y13 Esterilização masculina'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y14 Planejamento familiar, outros'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y16 Sinais/sintomas da mama masculina'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y24 Medo de disfunção sexual masculina'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y25 Medo de doença sexualmente transmissível'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y26 Medo de câncer genital masculino'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y27 Medo de doença genital masculina, outra'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Genital Masculino','SINAIS/SINTOMAS','#D0E3B9','Y29 Sinais/sintomas, outros'";
        Insert +="UNION ALL SELECT 'Genital Masculino','INFECÇÕES','#FAF3A0','Y70 Sífilis masculina'";
        Insert +="UNION ALL SELECT 'Genital Masculino','INFECÇÕES','#FAF3A0','Y71 Gonorréia masculina'";
        Insert +="UNION ALL SELECT 'Genital Masculino','INFECÇÕES','#FAF3A0','Y72 Herpes genital'";
        Insert +="UNION ALL SELECT 'Genital Masculino','INFECÇÕES','#FAF3A0','Y73 Prostatite/vesiculite seminal'";
        Insert +="UNION ALL SELECT 'Genital Masculino','INFECÇÕES','#FAF3A0','Y74 Orquite/epididimite'";
        Insert +="UNION ALL SELECT 'Genital Masculino','INFECÇÕES','#FAF3A0','Y75 Balanite/Balanopostite'";
        Insert +="UNION ALL SELECT 'Genital Masculino','INFECÇÕES','#FAF3A0','Y76 Condiloma acuminado'";
        Insert +="UNION ALL SELECT 'Genital Masculino','NEOPLASIAS','#D5EDF1','Y77 Neoplasia maligna da próstata'";
        Insert +="UNION ALL SELECT 'Genital Masculino','NEOPLASIAS','#D5EDF1','Y78 Neoplasia maligna genital masculina, outra'";
        Insert +="UNION ALL SELECT 'Genital Masculino','NEOPLASIAS','#D5EDF1','Y79 Neoplasia benigna genital masculina NE'";
        Insert +="UNION ALL SELECT 'Genital Masculino','TRAUMATISMOS','#F0A3A3','Y80 Traumatismo genital masculino, outro'";
        Insert +="UNION ALL SELECT 'Genital Masculino','ANOMALIAS CONGÉNITAS','#B3ADD1','Y81 Fimose/prepúcio redundante'";
        Insert +="UNION ALL SELECT 'Genital Masculino','ANOMALIAS CONGÉNITAS','#B3ADD1','Y82 Hipospádias'";
        Insert +="UNION ALL SELECT 'Genital Masculino','ANOMALIAS CONGÉNITAS','#B3ADD1','Y83 Testículo não descido/ Criptorquidia/ Testículo ectópico'";
        Insert +="UNION ALL SELECT 'Genital Masculino','ANOMALIAS CONGÉNITAS','#B3ADD1','Y84 Malformação genital congénita masculina, outra'";
        Insert +="UNION ALL SELECT 'Genital Masculino','OUTROS DIAGNÓSTICOS','#D5A8C9','Y85 Hipertrofia benigna da próstata/ hiperplasia prostática benigna'";
        Insert +="UNION ALL SELECT 'Genital Masculino','OUTROS DIAGNÓSTICOS','#D5A8C9','Y86 Hidrocele'";
        Insert +="UNION ALL SELECT 'Genital Masculino','OUTROS DIAGNÓSTICOS','#D5A8C9','Y99 Doença genital masculina, outra'";

        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z01 Pobreza/problemas econômicos'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z02 Problemas relacionados a água/alimentação'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z03 Problemas de habitação/vizinhança'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z04 Problema socio-cultural'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z05 Problemas com condições de trabalho'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z06 Problemas de desemprego'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z07 Problemas relacionados com educação'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z08 Problema relacionado com sistema de segurança social'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z09 Problema de ordem legal'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z10 Problema relacionado com sistema de saúde'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z11 Problema relacionado com estar doente'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z12 Problema de relacionamento com parceiro/ conjugal'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z13 Problema comportamental do parceiro/companheiro'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z14 Problema por doença do parceiro/companheiro'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z15 Perda ou falecimento do parceiro/companheiro'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z16 Problema de relacionamento com criança'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z18 Problema com criança doente'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z19 Perda ou falecimento de criança'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z20 Problema de relacionamento com familiares'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z21 Problema comportamental de familiar'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z22 Problema por doença familiar'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z23 Perda/falecimento de familiar'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z24 Problema de relacionamento com amigos'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z25 Ato ou acontecimento violento'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z27 Medo de problema social'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z28 Limitação funcional/incapacidade'";
        Insert +="UNION ALL SELECT 'Problemas Sociais','SINAIS/SINTOMAS','#D0E3B9','Z29 Problema social NE'";

        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        mydb.execSQL(Insert);
        mydb.close();
    }

    public void createTables() {
        try {
            mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("CREATE TABLE IF NOT EXISTS " + TB_CATEGORIA + " (" +
                    "CATEGORIA TEXT," +
                    "LETRA TEXT )");

            mydb.execSQL("CREATE TABLE IF NOT EXISTS " + TB_ITEM + " (" +
                    "CATEGORIA TEXT," +
                    "GRUPO TEXT," +
                    "COLORHEX TEXT," +
                    "ITEM TEXT )");

            mydb.close();
        } catch (Exception e) {
            Toast.makeText(Act, "Error in creating table", Toast.LENGTH_LONG).show();
        }
    }

    public boolean CheckData() {
        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        Cursor allrows = mydb.rawQuery("SELECT * FROM " + TB_CATEGORIA, null);
        if (allrows.getCount() == 0)
            return false;

        return true;
    }

    public List<Categoria> GetItensBy(String categoria) {
        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        Cursor allrows = mydb.rawQuery("SELECT ITEM,GRUPO FROM TB_ITEM WHERE CATEGORIA = '" + categoria + "'" , null);

        ArrayList<Categoria> List = new ArrayList<Categoria>();
        if (allrows.moveToFirst()) {
            do {

                Categoria NewI = new Categoria();
                NewI.Titulo = allrows.getString(0);
                NewI.Descricao = allrows.getString(1);
                List.add(NewI);
            }
            while (allrows.moveToNext());
        }
        mydb.close();
        return List;
    }

    public List<Categoria> GetItensBy(String categoria, String pesquisa) {
        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        Cursor allrows = mydb.rawQuery("SELECT ITEM,GRUPO FROM TB_ITEM WHERE CATEGORIA = '" + categoria + "' AND ITEM LIKE '%" + pesquisa + "%'", null);

        ArrayList<Categoria> List = new ArrayList<Categoria>();
        if (allrows.moveToFirst()) {
            do {

                Categoria NewI = new Categoria();
                NewI.Titulo = allrows.getString(0);
                NewI.Descricao = allrows.getString(1);
                List.add(NewI);
            }
            while (allrows.moveToNext());
        }
        mydb.close();
        return List;
    }

    public List<Categoria> GetItensByItem(String search) {
        mydb = Act.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        Cursor allrows = mydb.rawQuery("SELECT A.CATEGORIA,A.LETRA,B.GRUPO,B.ITEM FROM TB_CATEGORIA A " +
                " JOIN TB_ITEM B ON A.CATEGORIA = B.CATEGORIA" +
                " WHERE B.ITEM LIKE '%" + search + "%'", null);

        ArrayList<Categoria> List = new ArrayList<Categoria>();
        if (allrows.moveToFirst()) {
            do {

                Categoria NewI = new Categoria();
                NewI.Titulo = allrows.getString(3);
                NewI.Descricao = allrows.getString(2);
                NewI.Categoria =allrows.getString(0);
                NewI.Grupo =  allrows.getString(1);


                List.add(NewI);
            }
            while (allrows.moveToNext());
        }
        mydb.close();
        return List;
    }
}
