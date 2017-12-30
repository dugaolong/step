package cn.dgl.www.step.sqlite;

import java.util.List;

/**
 * Created by dugaolong on 17/12/28.
 */

public class WeatherData  {

    /**
     * status : OK
     * weather : [{"city_name":"长安","city_id":"CHSN000100","last_update":"2017-12-28T20:30:00+08:00","now":{"text":"晴","code":"1","temperature":"-1","feels_like":"-2","wind_direction":"南","wind_speed":"0.36","wind_scale":"0","humidity":"74","visibility":"2.00","pressure":"973","pressure_rising":"未知","air_quality":{"city":{"aqi":"343","pm25":"293","pm10":"383","so2":"51","no2":"159","co":"3.217","o3":"16","last_update":"2017-12-28T18:00:00+08:00","quality":"严重污染"},"stations":null},"alarms":[]},"today":{"sunrise":"07:49 AM","sunset":"05:42 PM","suggestion":{"dressing":{"brief":"较冷","details":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"uv":{"brief":"弱","details":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},"car_washing":{"brief":"较适宜","details":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"travel":{"brief":"适宜","details":"天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！"},"flu":{"brief":"极易发","details":"天气寒冷，昼夜温差极大且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"},"sport":{"brief":"较不宜","details":"天气较好，但考虑天气寒冷，推荐您进行室内运动，若在户外运动请注意保暖并做好准备活动。"}}},"future":[{"date":"2017-12-28","high":"8","low":"0","day":"周四","text":"晴/多云","code1":"0","code2":"4","cop":"","wind":"风力2级"},{"date":"2017-12-29","high":"10","low":"-3","day":"周五","text":"阴/多云","code1":"9","code2":"4","cop":"","wind":"风力3级"},{"date":"2017-12-30","high":"10","low":"0","day":"周六","text":"多云/晴","code1":"4","code2":"1","cop":"","wind":"风力3级"},{"date":"2017-12-31","high":"7","low":"-1","day":"周日","text":"多云/多云","code1":"4","code2":"4","cop":"","wind":"风力2级"},{"date":"2018-01-01","high":"8","low":"-1","day":"周一","text":"多云/阴","code1":"4","code2":"9","cop":"","wind":"风力2级"},{"date":"2018-01-02","high":"3","low":"0","day":"周二","text":"雨夹雪/雨夹雪","code1":"20","code2":"20","cop":"","wind":"风力2级"},{"date":"2018-01-03","high":"3","low":"-1","day":"周三","text":"雨夹雪/雨夹雪","code1":"20","code2":"20","cop":"","wind":"风力3级"},{"date":"2018-01-04","high":"1","low":"-4","day":"周四","text":"晴/阴","code1":"0","code2":"9","cop":"","wind":"风力2级"},{"date":"2018-01-05","high":"0","low":"-6","day":"周五","text":"中雪/中雪","code1":"23","code2":"23","cop":"","wind":"风力2级"},{"date":"2018-01-06","high":"0","low":"-7","day":"周六","text":"阴/阴","code1":"9","code2":"9","cop":"","wind":"风力2级"}]}]
     */

    private String status;
    private List<WeatherBean> weather;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<WeatherBean> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherBean> weather) {
        this.weather = weather;
    }

    public static class WeatherBean {
        /**
         * city_name : 长安
         * city_id : CHSN000100
         * last_update : 2017-12-28T20:30:00+08:00
         * now : {"text":"晴","code":"1","temperature":"-1","feels_like":"-2","wind_direction":"南","wind_speed":"0.36","wind_scale":"0","humidity":"74","visibility":"2.00","pressure":"973","pressure_rising":"未知","air_quality":{"city":{"aqi":"343","pm25":"293","pm10":"383","so2":"51","no2":"159","co":"3.217","o3":"16","last_update":"2017-12-28T18:00:00+08:00","quality":"严重污染"},"stations":null},"alarms":[]}
         * today : {"sunrise":"07:49 AM","sunset":"05:42 PM","suggestion":{"dressing":{"brief":"较冷","details":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"uv":{"brief":"弱","details":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},"car_washing":{"brief":"较适宜","details":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"travel":{"brief":"适宜","details":"天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！"},"flu":{"brief":"极易发","details":"天气寒冷，昼夜温差极大且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"},"sport":{"brief":"较不宜","details":"天气较好，但考虑天气寒冷，推荐您进行室内运动，若在户外运动请注意保暖并做好准备活动。"}}}
         * future : [{"date":"2017-12-28","high":"8","low":"0","day":"周四","text":"晴/多云","code1":"0","code2":"4","cop":"","wind":"风力2级"},{"date":"2017-12-29","high":"10","low":"-3","day":"周五","text":"阴/多云","code1":"9","code2":"4","cop":"","wind":"风力3级"},{"date":"2017-12-30","high":"10","low":"0","day":"周六","text":"多云/晴","code1":"4","code2":"1","cop":"","wind":"风力3级"},{"date":"2017-12-31","high":"7","low":"-1","day":"周日","text":"多云/多云","code1":"4","code2":"4","cop":"","wind":"风力2级"},{"date":"2018-01-01","high":"8","low":"-1","day":"周一","text":"多云/阴","code1":"4","code2":"9","cop":"","wind":"风力2级"},{"date":"2018-01-02","high":"3","low":"0","day":"周二","text":"雨夹雪/雨夹雪","code1":"20","code2":"20","cop":"","wind":"风力2级"},{"date":"2018-01-03","high":"3","low":"-1","day":"周三","text":"雨夹雪/雨夹雪","code1":"20","code2":"20","cop":"","wind":"风力3级"},{"date":"2018-01-04","high":"1","low":"-4","day":"周四","text":"晴/阴","code1":"0","code2":"9","cop":"","wind":"风力2级"},{"date":"2018-01-05","high":"0","low":"-6","day":"周五","text":"中雪/中雪","code1":"23","code2":"23","cop":"","wind":"风力2级"},{"date":"2018-01-06","high":"0","low":"-7","day":"周六","text":"阴/阴","code1":"9","code2":"9","cop":"","wind":"风力2级"}]
         */

        private String city_name;
        private String city_id;
        private String last_update;
        private NowBean now;
        private TodayBean today;
        private List<FutureBean> future;

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class NowBean {
            /**
             * text : 晴
             * code : 1
             * temperature : -1
             * feels_like : -2
             * wind_direction : 南
             * wind_speed : 0.36
             * wind_scale : 0
             * humidity : 74
             * visibility : 2.00
             * pressure : 973
             * pressure_rising : 未知
             * air_quality : {"city":{"aqi":"343","pm25":"293","pm10":"383","so2":"51","no2":"159","co":"3.217","o3":"16","last_update":"2017-12-28T18:00:00+08:00","quality":"严重污染"},"stations":null}
             * alarms : []
             */

            private String text;
            private String code;
            private String temperature;
            private String feels_like;
            private String wind_direction;
            private String wind_speed;
            private String wind_scale;
            private String humidity;
            private String visibility;
            private String pressure;
            private String pressure_rising;
            private AirQualityBean air_quality;
            private List<?> alarms;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getFeels_like() {
                return feels_like;
            }

            public void setFeels_like(String feels_like) {
                this.feels_like = feels_like;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }

            public String getWind_scale() {
                return wind_scale;
            }

            public void setWind_scale(String wind_scale) {
                this.wind_scale = wind_scale;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getPressure() {
                return pressure;
            }

            public void setPressure(String pressure) {
                this.pressure = pressure;
            }

            public String getPressure_rising() {
                return pressure_rising;
            }

            public void setPressure_rising(String pressure_rising) {
                this.pressure_rising = pressure_rising;
            }

            public AirQualityBean getAir_quality() {
                return air_quality;
            }

            public void setAir_quality(AirQualityBean air_quality) {
                this.air_quality = air_quality;
            }

            public List<?> getAlarms() {
                return alarms;
            }

            public void setAlarms(List<?> alarms) {
                this.alarms = alarms;
            }

            public static class AirQualityBean {
                /**
                 * city : {"aqi":"343","pm25":"293","pm10":"383","so2":"51","no2":"159","co":"3.217","o3":"16","last_update":"2017-12-28T18:00:00+08:00","quality":"严重污染"}
                 * stations : null
                 */

                private CityBean city;
                private Object stations;

                public CityBean getCity() {
                    return city;
                }

                public void setCity(CityBean city) {
                    this.city = city;
                }

                public Object getStations() {
                    return stations;
                }

                public void setStations(Object stations) {
                    this.stations = stations;
                }

                public static class CityBean {
                    /**
                     * aqi : 343
                     * pm25 : 293
                     * pm10 : 383
                     * so2 : 51
                     * no2 : 159
                     * co : 3.217
                     * o3 : 16
                     * last_update : 2017-12-28T18:00:00+08:00
                     * quality : 严重污染
                     */

                    private String aqi;
                    private String pm25;
                    private String pm10;
                    private String so2;
                    private String no2;
                    private String co;
                    private String o3;
                    private String last_update;
                    private String quality;

                    public String getAqi() {
                        return aqi;
                    }

                    public void setAqi(String aqi) {
                        this.aqi = aqi;
                    }

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getPm10() {
                        return pm10;
                    }

                    public void setPm10(String pm10) {
                        this.pm10 = pm10;
                    }

                    public String getSo2() {
                        return so2;
                    }

                    public void setSo2(String so2) {
                        this.so2 = so2;
                    }

                    public String getNo2() {
                        return no2;
                    }

                    public void setNo2(String no2) {
                        this.no2 = no2;
                    }

                    public String getCo() {
                        return co;
                    }

                    public void setCo(String co) {
                        this.co = co;
                    }

                    public String getO3() {
                        return o3;
                    }

                    public void setO3(String o3) {
                        this.o3 = o3;
                    }

                    public String getLast_update() {
                        return last_update;
                    }

                    public void setLast_update(String last_update) {
                        this.last_update = last_update;
                    }

                    public String getQuality() {
                        return quality;
                    }

                    public void setQuality(String quality) {
                        this.quality = quality;
                    }

                    @Override
                    public String toString() {
                        return "CityBean{" +
                                "aqi='" + aqi + '\'' +
                                ", pm25='" + pm25 + '\'' +
                                ", pm10='" + pm10 + '\'' +
                                ", so2='" + so2 + '\'' +
                                ", no2='" + no2 + '\'' +
                                ", co='" + co + '\'' +
                                ", o3='" + o3 + '\'' +
                                ", last_update='" + last_update + '\'' +
                                ", quality='" + quality + '\'' +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "AirQualityBean{" +
                            "city=" + city +
                            ", stations=" + stations +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "NowBean{" +
                        "text='" + text + '\'' +
                        ", code='" + code + '\'' +
                        ", temperature='" + temperature + '\'' +
                        ", feels_like='" + feels_like + '\'' +
                        ", wind_direction='" + wind_direction + '\'' +
                        ", wind_speed='" + wind_speed + '\'' +
                        ", wind_scale='" + wind_scale + '\'' +
                        ", humidity='" + humidity + '\'' +
                        ", visibility='" + visibility + '\'' +
                        ", pressure='" + pressure + '\'' +
                        ", pressure_rising='" + pressure_rising + '\'' +
                        ", air_quality=" + air_quality +
                        ", alarms=" + alarms +
                        '}';
            }
        }

        public static class TodayBean {
            /**
             * sunrise : 07:49 AM
             * sunset : 05:42 PM
             * suggestion : {"dressing":{"brief":"较冷","details":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"uv":{"brief":"弱","details":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},"car_washing":{"brief":"较适宜","details":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"travel":{"brief":"适宜","details":"天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！"},"flu":{"brief":"极易发","details":"天气寒冷，昼夜温差极大且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"},"sport":{"brief":"较不宜","details":"天气较好，但考虑天气寒冷，推荐您进行室内运动，若在户外运动请注意保暖并做好准备活动。"}}
             */

            private String sunrise;
            private String sunset;
            private SuggestionBean suggestion;

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public SuggestionBean getSuggestion() {
                return suggestion;
            }

            public void setSuggestion(SuggestionBean suggestion) {
                this.suggestion = suggestion;
            }

            public static class SuggestionBean {
                /**
                 * dressing : {"brief":"较冷","details":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
                 * uv : {"brief":"弱","details":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
                 * car_washing : {"brief":"较适宜","details":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
                 * travel : {"brief":"适宜","details":"天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！"}
                 * flu : {"brief":"极易发","details":"天气寒冷，昼夜温差极大且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"}
                 * sport : {"brief":"较不宜","details":"天气较好，但考虑天气寒冷，推荐您进行室内运动，若在户外运动请注意保暖并做好准备活动。"}
                 */

                private DressingBean dressing;
                private UvBean uv;
                private CarWashingBean car_washing;
                private TravelBean travel;
                private FluBean flu;
                private SportBean sport;

                public DressingBean getDressing() {
                    return dressing;
                }

                public void setDressing(DressingBean dressing) {
                    this.dressing = dressing;
                }

                public UvBean getUv() {
                    return uv;
                }

                public void setUv(UvBean uv) {
                    this.uv = uv;
                }

                public CarWashingBean getCar_washing() {
                    return car_washing;
                }

                public void setCar_washing(CarWashingBean car_washing) {
                    this.car_washing = car_washing;
                }

                public TravelBean getTravel() {
                    return travel;
                }

                public void setTravel(TravelBean travel) {
                    this.travel = travel;
                }

                public FluBean getFlu() {
                    return flu;
                }

                public void setFlu(FluBean flu) {
                    this.flu = flu;
                }

                public SportBean getSport() {
                    return sport;
                }

                public void setSport(SportBean sport) {
                    this.sport = sport;
                }

                public static class DressingBean {
                    /**
                     * brief : 较冷
                     * details : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }

                    @Override
                    public String toString() {
                        return "DressingBean{" +
                                "brief='" + brief + '\'' +
                                ", details='" + details + '\'' +
                                '}';
                    }
                }

                public static class UvBean {
                    /**
                     * brief : 弱
                     * details : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }

                    @Override
                    public String toString() {
                        return "UvBean{" +
                                "brief='" + brief + '\'' +
                                ", details='" + details + '\'' +
                                '}';
                    }
                }

                public static class CarWashingBean {
                    /**
                     * brief : 较适宜
                     * details : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }

                    @Override
                    public String toString() {
                        return "CarWashingBean{" +
                                "brief='" + brief + '\'' +
                                ", details='" + details + '\'' +
                                '}';
                    }
                }

                public static class TravelBean {
                    /**
                     * brief : 适宜
                     * details : 天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }

                    @Override
                    public String toString() {
                        return "TravelBean{" +
                                "brief='" + brief + '\'' +
                                ", details='" + details + '\'' +
                                '}';
                    }
                }

                public static class FluBean {
                    /**
                     * brief : 极易发
                     * details : 天气寒冷，昼夜温差极大且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }

                    @Override
                    public String toString() {
                        return "FluBean{" +
                                "brief='" + brief + '\'' +
                                ", details='" + details + '\'' +
                                '}';
                    }
                }

                public static class SportBean {
                    /**
                     * brief : 较不宜
                     * details : 天气较好，但考虑天气寒冷，推荐您进行室内运动，若在户外运动请注意保暖并做好准备活动。
                     */

                    private String brief;
                    private String details;

                    public String getBrief() {
                        return brief;
                    }

                    public void setBrief(String brief) {
                        this.brief = brief;
                    }

                    public String getDetails() {
                        return details;
                    }

                    public void setDetails(String details) {
                        this.details = details;
                    }

                    @Override
                    public String toString() {
                        return "SportBean{" +
                                "brief='" + brief + '\'' +
                                ", details='" + details + '\'' +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "SuggestionBean{" +
                            "dressing=" + dressing +
                            ", uv=" + uv +
                            ", car_washing=" + car_washing +
                            ", travel=" + travel +
                            ", flu=" + flu +
                            ", sport=" + sport +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "TodayBean{" +
                        "sunrise='" + sunrise + '\'' +
                        ", sunset='" + sunset + '\'' +
                        ", suggestion=" + suggestion +
                        '}';
            }
        }

        public static class FutureBean {
            /**
             * date : 2017-12-28
             * high : 8
             * low : 0
             * day : 周四
             * text : 晴/多云
             * code1 : 0
             * code2 : 4
             * cop :
             * wind : 风力2级
             */

            private String date;
            private String high;
            private String low;
            private String day;
            private String text;
            private String code1;
            private String code2;
            private String cop;
            private String wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
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

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getCode1() {
                return code1;
            }

            public void setCode1(String code1) {
                this.code1 = code1;
            }

            public String getCode2() {
                return code2;
            }

            public void setCode2(String code2) {
                this.code2 = code2;
            }

            public String getCop() {
                return cop;
            }

            public void setCop(String cop) {
                this.cop = cop;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            @Override
            public String toString() {
                return "FutureBean{" +
                        "date='" + date + '\'' +
                        ", high='" + high + '\'' +
                        ", low='" + low + '\'' +
                        ", day='" + day + '\'' +
                        ", text='" + text + '\'' +
                        ", code1='" + code1 + '\'' +
                        ", code2='" + code2 + '\'' +
                        ", cop='" + cop + '\'' +
                        ", wind='" + wind + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "WeatherBean{" +
                    "city_name='" + city_name + '\'' +
                    ", city_id='" + city_id + '\'' +
                    ", last_update='" + last_update + '\'' +
                    ", now=" + now +
                    ", today=" + today +
                    ", future=" + future +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "weather=" + weather +
                ", status='" + status + '\'' +
                '}';
    }
}
