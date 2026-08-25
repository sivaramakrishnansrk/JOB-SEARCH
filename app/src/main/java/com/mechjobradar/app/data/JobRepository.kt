package com.mechjobradar.app.data

import com.mechjobradar.app.model.CompanyTier
import com.mechjobradar.app.model.MechJobPost

object JobRepository {

    // Curated manufacturer dataset (South Indian hubs: Chennai, Coimbatore, Hosur, Bengaluru, Hyderabad)
    fun getProductionMechanicalJobs(): List<MechJobPost> {
        return listOf(
            MechJobPost(
                id = "MECH-001",
                title = "BIW Design Engineer (CATIA V5)",
                company = "Hyundai Motor India",
                tier = CompanyTier.OEM,
                domain = "CAD & Design",
                location = "Sriperumbudur, Chennai",
                minExpYears = 2,
                maxExpYears = 5,
                description = "Responsible for Body-in-White sheet metal fixture and stamping design. DFMEA and GD&T validation.",
                keySkills = listOf("CATIA V5", "GD&T", "DFMEA", "Sheet Metal"),
                applyUrl = "https://www.hyundai.com/in/en/hyundai-story/careers",
                postedHoursAgo = 2,
                sourcePlatform = "Official Portal"
            ),
            MechJobPost(
                id = "MECH-002",
                title = "Quality Assurance Lead - Casting & Forging",
                company = "Larsen & Toubro (L&T Valves)",
                tier = CompanyTier.HEAVY_ENG,
                domain = "QA & Inspection",
                location = "Coimbatore, Tamil Nadu",
                minExpYears = 3,
                maxExpYears = 7,
                description = "NDT Level-II certification required. Supervise valve body casting inspections, CMM verification, and APQP processes.",
                keySkills = listOf("NDT Level 2", "CMM", "APQP", "ISO 9001"),
                applyUrl = "https://www.larsentoubro.com/corporate/careers/",
                postedHoursAgo = 4,
                sourcePlatform = "Official Portal"
            ),
            MechJobPost(
                id = "MECH-003",
                title = "Powertrain & Transmission R&D Specialist",
                company = "TVS Motor Company",
                tier = CompanyTier.OEM,
                domain = "R&D / Thermal",
                location = "Hosur, Tamil Nadu",
                minExpYears = 1,
                maxExpYears = 4,
                description = "Design and thermal simulation of 2-wheeler/EV powertrain components using ANSYS & SolidWorks.",
                keySkills = listOf("ANSYS", "SolidWorks", "Thermal Analysis", "EV Powertrain"),
                applyUrl = "https://www.tvsmotor.com/careers",
                postedHoursAgo = 5,
                sourcePlatform = "Official Portal"
            ),
            MechJobPost(
                id = "MECH-004",
                title = "Brake Actuation Calibration Engineer",
                company = "Bosch India (Tier 1)",
                tier = CompanyTier.TIER_1,
                domain = "Automation / Mechatronics",
                location = "Adugodi, Bengaluru",
                minExpYears = 2,
                maxExpYears = 6,
                description = "Hydraulic and electronic brake system validation on chassis dynamometers and test rigs.",
                keySkills = listOf("MATLAB", "Hydraulics", "Testing Rig", "Automotive Validation"),
                applyUrl = "https://www.bosch.in/careers/",
                postedHoursAgo = 1,
                sourcePlatform = "LinkedIn Official"
            ),
            MechJobPost(
                id = "MECH-005",
                title = "Production & Assembly Line Supervisor",
                company = "Ashok Leyland",
                tier = CompanyTier.OEM,
                domain = "Production & Lean",
                location = "Ennore, Chennai",
                minExpYears = 0,
                maxExpYears = 3,
                description = "Direct commercial vehicle chassis assembly line. Implementation of 5S, Kaizen, and line balancing.",
                keySkills = listOf("5S", "Kaizen", "Line Balancing", "Lean Manufacturing"),
                applyUrl = "https://www.ashokleyland.com/careers",
                postedHoursAgo = 3,
                sourcePlatform = "Official Portal"
            ),
            MechJobPost(
                id = "MECH-006",
                title = "Aerospace CNC Programming & Tooling Engineer",
                company = "Titan Precision Engineering",
                tier = CompanyTier.PRECISION,
                domain = "Manufacturing & CNC",
                location = "Hosur, Tamil Nadu",
                minExpYears = 2,
                maxExpYears = 6,
                description = "5-axis Mastercam toolpath programming for aerospace turbine blisks and medical implants.",
                keySkills = listOf("Mastercam", "5-Axis CNC", "Siemens NX", "Titanium Machining"),
                applyUrl = "https://www.titancompany.in/careers",
                postedHoursAgo = 14,
                sourcePlatform = "Official Portal"
            ),
            MechJobPost(
                id = "MECH-007",
                title = "Thermal & HVAC Systems Design Engineer",
                company = "Valeo India",
                tier = CompanyTier.TIER_1,
                domain = "R&D / Thermal",
                location = "Navalur, Chennai",
                minExpYears = 3,
                maxExpYears = 8,
                description = "Compressor, radiator and HVAC module thermal load calculations using STAR-CCM+ and Creo.",
                keySkills = listOf("Creo Parametric", "STAR-CCM+", "CFD", "Heat Exchangers"),
                applyUrl = "https://www.valeo.com/en/careers/",
                postedHoursAgo = 22,
                sourcePlatform = "Official Portal"
            ),
            MechJobPost(
                id = "MECH-008",
                title = "Heavy Machinery Structural Design Engineer",
                company = "BEML Limited",
                tier = CompanyTier.HEAVY_ENG,
                domain = "CAD & Design",
                location = "Bengaluru / KGF",
                minExpYears = 1,
                maxExpYears = 5,
                description = "Structural weldment and FEA static analysis for mining dump trucks and metro coach bogies.",
                keySkills = listOf("FEA Analysis", "Ansys Mechanical", "Weldment Design", "Mining Gear"),
                applyUrl = "https://www.bemlindia.in/careers.aspx",
                postedHoursAgo = 30,
                sourcePlatform = "Official Portal"
            )
        )
    }
}
