/**
 * Created by Conner on 4/28/17.
 */

package com.simmeringc.websitePoller.controllers;

import static com.simmeringc.websitePoller.controllers.WebRequester.getHtml;
import static com.simmeringc.websitePoller.controllers.LetterPairSimilarity.compareStrings;
import static com.simmeringc.websitePoller.views.SystemLog.systemLogHtmlGetFailed;

public class WebPoller implements Runnable {
    public String url;
    public String oldHtml;
    public String newHtml;
    public int trackerNumber;
    public double thresholdPercent;
    public double percentDiff;

    public WebPoller(String url, String oldHtml, int trackerNumber, double thresholdPercent) {
        this.url = url;
        this.oldHtml = oldHtml;
        this.trackerNumber = trackerNumber;
        this.thresholdPercent = thresholdPercent;
    }

    public void run() {
        try {
            newHtml = getHtml(url);
            percentDiff = compareStrings(oldHtml, newHtml);
            System.out.println(percentDiff);
        } catch (Exception ex) {
            systemLogHtmlGetFailed(url);
            ex.printStackTrace();
        }

    }
}
