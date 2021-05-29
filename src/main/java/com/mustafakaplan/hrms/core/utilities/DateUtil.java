package com.mustafakaplan.hrms.core.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String convertDateToTrStringFormatFromTimestamp(Date date) {
        SimpleDateFormat sdfTr = new SimpleDateFormat("dd/MM/yyyy", new Locale("tr"));
        return sdfTr.format(new Date(date.getTime()));
    }
}
