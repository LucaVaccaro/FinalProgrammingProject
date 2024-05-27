package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public Address(int streetNo, String street, String city, String province, String postalCode, String country) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
            this.country = country;
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
            this.country = null;
        }
    }

    /**
     * checks if a postcode is valid or not. The length of a postal code can either be 6 or 7,
     * if the length is 6, then it must follow the format: CDCDCD,
     * if the length is 7, then it must follow the format: CDC DCD.
     * where C is a character, while D is a digit. Case-insensitive;
     * @param postalCode
     * @return true  is the postal code is valid and false if it isn't
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null) {
            return false;
        }

        postalCode = postalCode.toUpperCase();
        int length = postalCode.length();

        if (length != 6 && length != 7) {
            return false;
        }

        if (length == 6) {

            for (int i = 0; i < length; i++) {
                char c = postalCode.charAt(i);
                if (i % 2 == 0) {
                    if (!(c >= 'A' && c <= 'Z')) {
                        return false;
                    }
                } else {
                    if (!(c >= '0' && c <= '9')) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            if (postalCode.charAt(3) != ' ') {
                return false;
            }
            for (int i = 0; i < length; i++) {
                char c = postalCode.charAt(i);
                if (i == 3) continue;
                if (i < 3) {
                    if (i % 2 == 0) {
                        if (!(c >= 'A' && c <= 'Z')) {
                            return false;
                        }
                    } else {
                        if (!(c >= '0' && c <= '9')) {
                            return false;
                        }
                    }
                } else {
                    if ((i - 1) % 2 == 0) {
                        if (!(c >= 'A' && c <= 'Z')) {
                            return false;
                        }
                    } else {
                        if (!(c >= '0' && c <= '9')) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
