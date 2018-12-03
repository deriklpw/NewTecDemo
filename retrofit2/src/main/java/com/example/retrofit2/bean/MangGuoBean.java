package com.example.retrofit2.bean;

/**
 * Created by Derik on 2018/10/22.
 * Email: weilai0314@163.com
 */

public class MangGuoBean {

    /**
     * status : 0
     * msg : success
     * path : http://xxx.da.mgtv.com/app/player
     * platform : iphone
     * param : {"p":{"m":{"p":4580,"allowad":100000,"ptype":"front"},"u":{"cxid":"6778_test_9"},"c":{"type":34,"os":"ios_11.0.1","version":"bili-iphone-5.7.8","brand":"apple","mn":"iPhone9,1","idfa":"153D6237-AF0E-40E3-AAAB-402CAE93D8F5","ua":"Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_1 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Mobile/15A402 blbl-iPhone-appstore/5.5.4.171012","openudid":"649AF3681318489643DEC3A6307993BB","ip":"124.232.146.135","did":"1412281A4AD14C4B4B119669C88B1CF6","cx_tag":"v3","cx_type":"2","cx_plan":"201800"},"parameter":48},"v":{"v":{"id":4295209,"hid":321779}}}
     */

    private int status;
    private String msg;
    private String path;
    private String platform;
    private ParamBean param;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public static class ParamBean {
        /**
         * p : {"m":{"p":4580,"allowad":100000,"ptype":"front"},"u":{"cxid":"6778_test_9"},"c":{"type":34,"os":"ios_11.0.1","version":"bili-iphone-5.7.8","brand":"apple","mn":"iPhone9,1","idfa":"153D6237-AF0E-40E3-AAAB-402CAE93D8F5","ua":"Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_1 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Mobile/15A402 blbl-iPhone-appstore/5.5.4.171012","openudid":"649AF3681318489643DEC3A6307993BB","ip":"124.232.146.135","did":"1412281A4AD14C4B4B119669C88B1CF6","cx_tag":"v3","cx_type":"2","cx_plan":"201800"},"parameter":48}
         * v : {"v":{"id":4295209,"hid":321779}}
         */

        private PBean p;
        private VBeanX v;

        public PBean getP() {
            return p;
        }

        public void setP(PBean p) {
            this.p = p;
        }

        public VBeanX getV() {
            return v;
        }

        public void setV(VBeanX v) {
            this.v = v;
        }

        public static class PBean {
            /**
             * m : {"p":4580,"allowad":100000,"ptype":"front"}
             * u : {"cxid":"6778_test_9"}
             * c : {"type":34,"os":"ios_11.0.1","version":"bili-iphone-5.7.8","brand":"apple","mn":"iPhone9,1","idfa":"153D6237-AF0E-40E3-AAAB-402CAE93D8F5","ua":"Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_1 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Mobile/15A402 blbl-iPhone-appstore/5.5.4.171012","openudid":"649AF3681318489643DEC3A6307993BB","ip":"124.232.146.135","did":"1412281A4AD14C4B4B119669C88B1CF6","cx_tag":"v3","cx_type":"2","cx_plan":"201800"}
             * parameter : 48
             */

            private MBean m;
            private UBean u;
            private CBean c;
            private int parameter;

            public MBean getM() {
                return m;
            }

            public void setM(MBean m) {
                this.m = m;
            }

            public UBean getU() {
                return u;
            }

            public void setU(UBean u) {
                this.u = u;
            }

            public CBean getC() {
                return c;
            }

            public void setC(CBean c) {
                this.c = c;
            }

            public int getParameter() {
                return parameter;
            }

            public void setParameter(int parameter) {
                this.parameter = parameter;
            }

            public static class MBean {
                /**
                 * p : 4580
                 * allowad : 100000
                 * ptype : front
                 */

                private int p;
                private int allowad;
                private String ptype;

                public int getP() {
                    return p;
                }

                public void setP(int p) {
                    this.p = p;
                }

                public int getAllowad() {
                    return allowad;
                }

                public void setAllowad(int allowad) {
                    this.allowad = allowad;
                }

                public String getPtype() {
                    return ptype;
                }

                public void setPtype(String ptype) {
                    this.ptype = ptype;
                }
            }

            public static class UBean {
                /**
                 * cxid : 6778_test_9
                 */

                private String cxid;

                public String getCxid() {
                    return cxid;
                }

                public void setCxid(String cxid) {
                    this.cxid = cxid;
                }
            }

            public static class CBean {
                /**
                 * type : 34
                 * os : ios_11.0.1
                 * version : bili-iphone-5.7.8
                 * brand : apple
                 * mn : iPhone9,1
                 * idfa : 153D6237-AF0E-40E3-AAAB-402CAE93D8F5
                 * ua : Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_1 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Mobile/15A402 blbl-iPhone-appstore/5.5.4.171012
                 * openudid : 649AF3681318489643DEC3A6307993BB
                 * ip : 124.232.146.135
                 * did : 1412281A4AD14C4B4B119669C88B1CF6
                 * cx_tag : v3
                 * cx_type : 2
                 * cx_plan : 201800
                 */

                private int type;
                private String os;
                private String version;
                private String brand;
                private String mn;
                private String idfa;
                private String ua;
                private String openudid;
                private String ip;
                private String did;
                private String cx_tag;
                private String cx_type;
                private String cx_plan;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getOs() {
                    return os;
                }

                public void setOs(String os) {
                    this.os = os;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public String getBrand() {
                    return brand;
                }

                public void setBrand(String brand) {
                    this.brand = brand;
                }

                public String getMn() {
                    return mn;
                }

                public void setMn(String mn) {
                    this.mn = mn;
                }

                public String getIdfa() {
                    return idfa;
                }

                public void setIdfa(String idfa) {
                    this.idfa = idfa;
                }

                public String getUa() {
                    return ua;
                }

                public void setUa(String ua) {
                    this.ua = ua;
                }

                public String getOpenudid() {
                    return openudid;
                }

                public void setOpenudid(String openudid) {
                    this.openudid = openudid;
                }

                public String getIp() {
                    return ip;
                }

                public void setIp(String ip) {
                    this.ip = ip;
                }

                public String getDid() {
                    return did;
                }

                public void setDid(String did) {
                    this.did = did;
                }

                public String getCx_tag() {
                    return cx_tag;
                }

                public void setCx_tag(String cx_tag) {
                    this.cx_tag = cx_tag;
                }

                public String getCx_type() {
                    return cx_type;
                }

                public void setCx_type(String cx_type) {
                    this.cx_type = cx_type;
                }

                public String getCx_plan() {
                    return cx_plan;
                }

                public void setCx_plan(String cx_plan) {
                    this.cx_plan = cx_plan;
                }
            }
        }

        public static class VBeanX {
            /**
             * v : {"id":4295209,"hid":321779}
             */

            private VBean v;

            public VBean getV() {
                return v;
            }

            public void setV(VBean v) {
                this.v = v;
            }

            public static class VBean {
                /**
                 * id : 4295209
                 * hid : 321779
                 */

                private int id;
                private int hid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getHid() {
                    return hid;
                }

                public void setHid(int hid) {
                    this.hid = hid;
                }
            }
        }
    }
}
