package th.co.cinfo.chumchon.models;

/**
 * Created by JuJiiz on 27/7/2560.
 */

public class ModelTranslate {
    public static String transFormIDtoFormName(String strFORM){

        if(strFORM.equals("F01_01"))
            strFORM = "ครัวเรือน";
        if(strFORM.equals("F01_02"))
            strFORM = "ประชากร";
        if(strFORM.equals("F01_03"))
            strFORM = "ที่ดิน";
        if(strFORM.equals("F01_04"))
            strFORM = "อาคาร";
        if(strFORM.equals("F01_05"))
            strFORM = "ป้าย";
        if(strFORM.equals("F01_06"))
            strFORM = "ยุ้งข้าว";
        if(strFORM.equals("F01_07"))
            strFORM = "พาหนะ";
        if(strFORM.equals("F01_08"))
            strFORM = "กิจการพาณิชย์";
        if(strFORM.equals("F01_09"))
            strFORM = "โรงงาน";
        if(strFORM.equals("F01_10"))
            strFORM = "ตลาด";
        if(strFORM.equals("F01_11"))
            strFORM = "ที่จำหน่ายอาหาร";
        if(strFORM.equals("F01_12"))
            strFORM = "โรงฆ่าสัตว์";
        if(strFORM.equals("F01_13"))
            strFORM = "ป่าไม้";
        if(strFORM.equals("F01_14"))
            strFORM = "หมืองแร่";
        if(strFORM.equals("F01_15"))
            strFORM = "สถานที่ท่องเที่ยว";
        if(strFORM.equals("F01_16"))
            strFORM = "สถานที่พักผ่อนหย่อนใจ";
        if(strFORM.equals("F01_17"))
            strFORM = "เจ้าของสุสานและฌาปนสถาน";
        if(strFORM.equals("F01_18"))
            strFORM = "สถานีจ่ายเชื้อเพลิง";
        if(strFORM.equals("F01_19"))
            strFORM = "ที่จอดรถ";
        if(strFORM.equals("F01_20"))
            strFORM = "ห้องน้ำสาธารณะ";
        if(strFORM.equals("F01_21"))
            strFORM = "สถานที่ประชุม";
        if(strFORM.equals("F01_22"))
            strFORM = "สถานีสื่อสารมวลชน";
        if(strFORM.equals("F02_01"))
            strFORM = "องค์กรปกครองส่วนท้องถิ่น";
        if(strFORM.equals("F02_02"))
            strFORM = "บุคลากรท้องถิ่น";
        if(strFORM.equals("F02_03"))
            strFORM = "โครงการท้องถิ่น";
        if(strFORM.equals("F02_04"))
            strFORM = "ชุมชน/หมู่บ้าน";
        if(strFORM.equals("F02_05"))
            strFORM = "สนามกีฬา";
        if(strFORM.equals("F02_06"))
            strFORM = "ศาสนสถาน";
        if(strFORM.equals("F02_07"))
            strFORM = "สถานศึกษา";
        if(strFORM.equals("F02_08"))
            strFORM = "ศูนย์การเรียนรู้";
        if(strFORM.equals("F02_09"))
            strFORM = "สถานบริการสาธารณสุข";
        if(strFORM.equals("F02_10"))
            strFORM = "สถานธนานุบาล";
        if(strFORM.equals("F02_11"))
            strFORM = "สถานีขนส่ง";
        if(strFORM.equals("F02_12"))
            strFORM = "ถนน";
        if(strFORM.equals("F02_13"))
            strFORM = "สะพาน";
        if(strFORM.equals("F02_14"))
            strFORM = "ทางน้ำ";
        if(strFORM.equals("F02_15"))
            strFORM = "แหล่งน้ำ";
        if(strFORM.equals("F02_16"))
            strFORM = "ระบบไฟฟ้า";
        if(strFORM.equals("F02_17"))
            strFORM = "ระบบประปา";
        if(strFORM.equals("F02_18"))
            strFORM = "ระบบบำบัดน้ำเสีย";
        if(strFORM.equals("F02_19"))
            strFORM = "ระบบจัดการขยะ";
        if(strFORM.equals("F02_20"))
            strFORM = "ระบบแจ้งเตือนภาย";
        if(strFORM.equals("F02_21"))
            strFORM = "ระบบกล้องวงจรปิด";
        if(strFORM.equals("F02_22"))
            strFORM = "ลภัย";
        return strFORM;
    }

    public static String transStatus(String strSTATUS){
        if(strSTATUS.equals("success"))
            strSTATUS = "บันทึกสำเร็จ";
        if(strSTATUS.equals("surveying"))
            strSTATUS = "ระหว่างสำรวจ";
        if(strSTATUS.equals("waiting"))
            strSTATUS = "รอการสำรวจ";
        return strSTATUS;
    }
}
