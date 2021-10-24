package ir.sk.atm.others;

import java.util.Objects;

public enum MenuOption {

    BALANCE_INQUIRY(1, "http"),
    WITHDRAWAL(2, "ftp"),
    DEPOSIT(3, "https"),
    EXIT_ATM(4, "https");

    private Integer value;
    private String desc;

    MenuOption(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


    // static factory method
    public static MenuOption valueOf(Integer type) {
        for (MenuOption code : MenuOption.values()) {
            if (Objects.equals(type, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    // static factory method
    public static MenuOption valueOfByDesc(String desc) {
        for (MenuOption code : MenuOption.values()) {
            if (desc.equals(code.getDesc())) {
                return code;
            }
        }
        return null;
    }
}
