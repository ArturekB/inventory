package pl.com.bottega.inventory.api.dto;

import java.util.Map;

public class ReportBuilder {

    public static PurchaseReport createReport(boolean b, Map<String, Long> map) {
        return b? new SuccessReport(map):new FailReport(map);
    }


}
