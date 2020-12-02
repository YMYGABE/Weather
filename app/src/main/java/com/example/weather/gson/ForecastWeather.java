package com.example.weather.gson;

import java.util.List;

public class ForecastWeather {


    /**
     * status : 1
     * count : 1
     * info : OK
     * infocode : 10000
     * forecasts : [{"city":"湖滨区","adcode":"411202","province":"河南","reporttime":"2020-11-23 17:21:59","casts":[{"date":"2020-11-23","week":"1","dayweather":"小雪","nightweather":"多云","daytemp":"1","nighttemp":"-3","daywind":"东南","nightwind":"东南","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-24","week":"2","dayweather":"小雨","nightweather":"多云","daytemp":"4","nighttemp":"0","daywind":"东","nightwind":"东","daypower":"4","nightpower":"4"},{"date":"2020-11-25","week":"3","dayweather":"多云","nightweather":"多云","daytemp":"5","nighttemp":"1","daywind":"西","nightwind":"西","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-26","week":"4","dayweather":"小雨","nightweather":"多云","daytemp":"7","nighttemp":"0","daywind":"东南","nightwind":"东南","daypower":"4","nightpower":"4"}]}]
     */

    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<ForecastsBean> forecasts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public List<ForecastsBean> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastsBean> forecasts) {
        this.forecasts = forecasts;
    }

    public static class ForecastsBean {
        /**
         * city : 湖滨区
         * adcode : 411202
         * province : 河南
         * reporttime : 2020-11-23 17:21:59
         * casts : [{"date":"2020-11-23","week":"1","dayweather":"小雪","nightweather":"多云","daytemp":"1","nighttemp":"-3","daywind":"东南","nightwind":"东南","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-24","week":"2","dayweather":"小雨","nightweather":"多云","daytemp":"4","nighttemp":"0","daywind":"东","nightwind":"东","daypower":"4","nightpower":"4"},{"date":"2020-11-25","week":"3","dayweather":"多云","nightweather":"多云","daytemp":"5","nighttemp":"1","daywind":"西","nightwind":"西","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-26","week":"4","dayweather":"小雨","nightweather":"多云","daytemp":"7","nighttemp":"0","daywind":"东南","nightwind":"东南","daypower":"4","nightpower":"4"}]
         */

        private String city;
        private String adcode;
        private String province;
        private String reporttime;
        private List<CastsBean> casts;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getReporttime() {
            return reporttime;
        }

        public void setReporttime(String reporttime) {
            this.reporttime = reporttime;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public static class CastsBean {
            /**
             * date : 2020-11-23
             * week : 1
             * dayweather : 小雪
             * nightweather : 多云
             * daytemp : 1
             * nighttemp : -3
             * daywind : 东南
             * nightwind : 东南
             * daypower : ≤3
             * nightpower : ≤3
             */

            private String date;
            private String week;
            private String dayweather;
            private String nightweather;
            private String daytemp;
            private String nighttemp;
            private String daywind;
            private String nightwind;
            private String daypower;
            private String nightpower;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDayweather() {
                return dayweather;
            }

            public void setDayweather(String dayweather) {
                this.dayweather = dayweather;
            }

            public String getNightweather() {
                return nightweather;
            }

            public void setNightweather(String nightweather) {
                this.nightweather = nightweather;
            }

            public String getDaytemp() {
                return daytemp;
            }

            public void setDaytemp(String daytemp) {
                this.daytemp = daytemp;
            }

            public String getNighttemp() {
                return nighttemp;
            }

            public void setNighttemp(String nighttemp) {
                this.nighttemp = nighttemp;
            }

            public String getDaywind() {
                return daywind;
            }

            public void setDaywind(String daywind) {
                this.daywind = daywind;
            }

            public String getNightwind() {
                return nightwind;
            }

            public void setNightwind(String nightwind) {
                this.nightwind = nightwind;
            }

            public String getDaypower() {
                return daypower;
            }

            public void setDaypower(String daypower) {
                this.daypower = daypower;
            }

            public String getNightpower() {
                return nightpower;
            }

            public void setNightpower(String nightpower) {
                this.nightpower = nightpower;
            }
        }
    }
}

