package io.benjyair.rukia;

import java.util.List;

public class Weather {

    /**
     * time : 2018-10-24 23:10:37
     * cityInfo : {"city":"朝阳区","cityId":"101010300","parent":"北京市","updateTime":"23:00"}
     * date : 20181024
     * message : Success !
     * status : 200
     * data : {"shidu":"73%","pm25":56,"pm10":112,"quality":"良","wendu":"9","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"23日星期二","sunrise":"06:31","high":"高温 19.0℃","low":"低温 3.0℃","sunset":"17:25","aqi":51,"fx":"西南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},"forecast":[{"date":"24日星期三","sunrise":"06:32","high":"高温 21.0℃","low":"低温 6.0℃","sunset":"17:24","aqi":69,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"25日星期四","sunrise":"06:33","high":"高温 16.0℃","low":"低温 7.0℃","sunset":"17:22","aqi":224,"fx":"西北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"26日星期五","sunrise":"06:34","high":"高温 14.0℃","low":"低温 3.0℃","sunset":"17:21","aqi":55,"fx":"西北风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"27日星期六","sunrise":"06:36","high":"高温 17.0℃","low":"低温 3.0℃","sunset":"17:20","aqi":39,"fx":"西北风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"28日星期日","sunrise":"06:37","high":"高温 15.0℃","low":"低温 5.0℃","sunset":"17:18","aqi":53,"fx":"西北风","fl":"4-5级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]}
     */

    private String time;
    private CityInfoBean cityInfo;
    private String date;
    private String message;
    private int status;
    private DataBean data;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CityInfoBean getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoBean cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class CityInfoBean {
        /**
         * city : 朝阳区
         * cityId : 101010300
         * parent : 北京市
         * updateTime : 23:00
         */

        private String city;
        private String cityId;
        private String parent;
        private String updateTime;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class DataBean {
        /**
         * shidu : 73%
         * pm25 : 56.0
         * pm10 : 112.0
         * quality : 良
         * wendu : 9
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"23日星期二","sunrise":"06:31","high":"高温 19.0℃","low":"低温 3.0℃","sunset":"17:25","aqi":51,"fx":"西南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}
         * forecast : [{"date":"24日星期三","sunrise":"06:32","high":"高温 21.0℃","low":"低温 6.0℃","sunset":"17:24","aqi":69,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"25日星期四","sunrise":"06:33","high":"高温 16.0℃","low":"低温 7.0℃","sunset":"17:22","aqi":224,"fx":"西北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"26日星期五","sunrise":"06:34","high":"高温 14.0℃","low":"低温 3.0℃","sunset":"17:21","aqi":55,"fx":"西北风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"27日星期六","sunrise":"06:36","high":"高温 17.0℃","low":"低温 3.0℃","sunset":"17:20","aqi":39,"fx":"西北风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"28日星期日","sunrise":"06:37","high":"高温 15.0℃","low":"低温 5.0℃","sunset":"17:18","aqi":53,"fx":"西北风","fl":"4-5级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]
         */

        private String shidu;
        private double pm25;
        private double pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public double getPm10() {
            return pm10;
        }

        public void setPm10(double pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 23日星期二
             * sunrise : 06:31
             * high : 高温 19.0℃
             * low : 低温 3.0℃
             * sunset : 17:25
             * aqi : 51.0
             * fx : 西南风
             * fl : <3级
             * type : 晴
             * notice : 愿你拥有比阳光明媚的心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private double aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            /**
             * date : 24日星期三
             * sunrise : 06:32
             * high : 高温 21.0℃
             * low : 低温 6.0℃
             * sunset : 17:24
             * aqi : 69.0
             * fx : 南风
             * fl : <3级
             * type : 晴
             * notice : 愿你拥有比阳光明媚的心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private double aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
