package com.notsauce.parkd.controllers;

import com.notsauce.parkd.models.Alert;
import com.notsauce.parkd.models.NpsAlertService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.util.List;

@Controller
public class AlertsController {

    private final NpsAlertService alertService;

    public AlertsController(NpsAlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/alerts")
    public String showAlertsPage(Model model) {
        try {
            List<Alert> alerts = alertService.getAlerts();
            model.addAttribute("alerts", alerts);
        } catch (IOException e) {
            model.addAttribute("error", "Unable to fetch alerts at this time.");
        }
        return "alerts";
    }
}

