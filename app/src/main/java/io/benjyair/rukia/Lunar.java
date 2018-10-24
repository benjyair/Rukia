package io.benjyair.rukia;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Lunar {

    /**
     * status : 200
     * message : success
     * data : {"year":2018,"month":10,"day":25,"lunarYear":2018,"lunarMonth":9,"lunarDay":17,"cnyear":"贰零壹捌 ","cnmonth":"九","cnday":"十七","hyear":"戊戌","cyclicalYear":"戊戌","cyclicalMonth":"壬戌","cyclicalDay":"庚寅","suit":"捕捉,结网,入殓,除服,成服,移柩,破土,安葬,启钻,立碑","taboo":"嫁娶,祭祀,入宅,盖屋,移徙","animal":"狗","week":"Thursday","festivalList":[],"jieqi":{"9":"寒露","24":"霜降"},"maxDayInMonth":30,"leap":false,"lunarYearString":"戊戌","bigMonth":true}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * year : 2018
         * month : 10
         * day : 25
         * lunarYear : 2018
         * lunarMonth : 9
         * lunarDay : 17
         * cnyear : 贰零壹捌
         * cnmonth : 九
         * cnday : 十七
         * hyear : 戊戌
         * cyclicalYear : 戊戌
         * cyclicalMonth : 壬戌
         * cyclicalDay : 庚寅
         * suit : 捕捉,结网,入殓,除服,成服,移柩,破土,安葬,启钻,立碑
         * taboo : 嫁娶,祭祀,入宅,盖屋,移徙
         * animal : 狗
         * week : Thursday
         * festivalList : []
         * jieqi : {"9":"寒露","24":"霜降"}
         * maxDayInMonth : 30
         * leap : false
         * lunarYearString : 戊戌
         * bigMonth : true
         */

        private int year;
        private int month;
        private int day;
        private int lunarYear;
        private int lunarMonth;
        private int lunarDay;
        private String cnyear;
        private String cnmonth;
        private String cnday;
        private String hyear;
        private String cyclicalYear;
        private String cyclicalMonth;
        private String cyclicalDay;
        private String suit;
        private String taboo;
        private String animal;
        private String week;
        private JieqiBean jieqi;
        private int maxDayInMonth;
        private boolean leap;
        private String lunarYearString;
        private boolean bigMonth;
        private List<?> festivalList;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getLunarYear() {
            return lunarYear;
        }

        public void setLunarYear(int lunarYear) {
            this.lunarYear = lunarYear;
        }

        public int getLunarMonth() {
            return lunarMonth;
        }

        public void setLunarMonth(int lunarMonth) {
            this.lunarMonth = lunarMonth;
        }

        public int getLunarDay() {
            return lunarDay;
        }

        public void setLunarDay(int lunarDay) {
            this.lunarDay = lunarDay;
        }

        public String getCnyear() {
            return cnyear;
        }

        public void setCnyear(String cnyear) {
            this.cnyear = cnyear;
        }

        public String getCnmonth() {
            return cnmonth;
        }

        public void setCnmonth(String cnmonth) {
            this.cnmonth = cnmonth;
        }

        public String getCnday() {
            return cnday;
        }

        public void setCnday(String cnday) {
            this.cnday = cnday;
        }

        public String getHyear() {
            return hyear;
        }

        public void setHyear(String hyear) {
            this.hyear = hyear;
        }

        public String getCyclicalYear() {
            return cyclicalYear;
        }

        public void setCyclicalYear(String cyclicalYear) {
            this.cyclicalYear = cyclicalYear;
        }

        public String getCyclicalMonth() {
            return cyclicalMonth;
        }

        public void setCyclicalMonth(String cyclicalMonth) {
            this.cyclicalMonth = cyclicalMonth;
        }

        public String getCyclicalDay() {
            return cyclicalDay;
        }

        public void setCyclicalDay(String cyclicalDay) {
            this.cyclicalDay = cyclicalDay;
        }

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

        public String getTaboo() {
            return taboo;
        }

        public void setTaboo(String taboo) {
            this.taboo = taboo;
        }

        public String getAnimal() {
            return animal;
        }

        public void setAnimal(String animal) {
            this.animal = animal;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public JieqiBean getJieqi() {
            return jieqi;
        }

        public void setJieqi(JieqiBean jieqi) {
            this.jieqi = jieqi;
        }

        public int getMaxDayInMonth() {
            return maxDayInMonth;
        }

        public void setMaxDayInMonth(int maxDayInMonth) {
            this.maxDayInMonth = maxDayInMonth;
        }

        public boolean isLeap() {
            return leap;
        }

        public void setLeap(boolean leap) {
            this.leap = leap;
        }

        public String getLunarYearString() {
            return lunarYearString;
        }

        public void setLunarYearString(String lunarYearString) {
            this.lunarYearString = lunarYearString;
        }

        public boolean isBigMonth() {
            return bigMonth;
        }

        public void setBigMonth(boolean bigMonth) {
            this.bigMonth = bigMonth;
        }

        public List<?> getFestivalList() {
            return festivalList;
        }

        public void setFestivalList(List<?> festivalList) {
            this.festivalList = festivalList;
        }

        public static class JieqiBean {
            /**
             * 9 : 寒露
             * 24 : 霜降
             */

            @SerializedName("9")
            private String _$9;
            @SerializedName("24")
            private String _$24;

            public String get_$9() {
                return _$9;
            }

            public void set_$9(String _$9) {
                this._$9 = _$9;
            }

            public String get_$24() {
                return _$24;
            }

            public void set_$24(String _$24) {
                this._$24 = _$24;
            }
        }
    }
}
