package com.mechjobradar.app.data

import com.mechjobradar.app.model.CompanyTier
import com.mechjobradar.app.model.MechJobPost

object JobRepository {

    fun getTamilNaduManufacturingJobs(): List<MechJobPost> {
        return listOf(
            MechJobPost(
                id = "TN-DAIMLER-01",
                title = "Truck Chassis & Axle Assembly Engineer",
                company = "Daimler India Commercial Vehicles (DICV)",
                tier = CompanyTier.OEM,
                domain = "Production & Assembly",
                plantLocation = "Oragadam, Chennai",
                minExpYears = 2,
                maxExpYears = 5,
                description = "Manage BharatBenz heavy commercial vehicle assembly line. Line balancing, Torque audit, and takt time optimization.",
                keySkills = listOf("Assembly Line", "Torque Audit", "Line Balancing", "5S", "Lean Mfg"),
                applyUrl = "https://www.daimler-truck.com/en/career",
                postedHoursAgo = 1,
                sourcePlatform = "Daimler Careers"
            ),
            MechJobPost(
                id = "TN-RE-02",
                title = "Powertrain Machining & CNC Quality Lead",
                company = "Royal Enfield (Eicher Motors)",
                tier = CompanyTier.OEM,
                domain = "QA & Machining",
                plantLocation = "Vallam Vadagal / Oragadam",
                minExpYears = 3,
                maxExpYears = 7,
                description = "Engine crankcase & cylinder head machining line quality inspection. SPC, MSA, and coordinate measuring machine (CMM) reports.",
                keySkills = listOf("CMM Inspection", "SPC / MSA", "Engine Powertrain", "GD&T"),
                applyUrl = "https://www.royalenfield.com/in/en/careers/",
                postedHoursAgo = 2,
                sourcePlatform = "Royal Enfield Portal"
            ),
            MechJobPost(
                id = "TN-RNTBCI-03",
                title = "BIW Crash & NVH CAE Simulation Engineer",
                company = "Renault Nissan Technology (RNTBCI)",
                tier = CompanyTier.OEM,
                domain = "CAE / Simulation",
                plantLocation = "Mahindra World City / Oragadam",
                minExpYears = 2,
                maxExpYears = 6,
                description = "Non-linear structural crash analysis and durability simulations on vehicle platforms using LS-DYNA, Primer, and HyperMesh.",
                keySkills = listOf("LS-DYNA", "HyperMesh", "Crash CAE", "NVH", "ANSA"),
                applyUrl = "https://www.rntbci.com/careers",
                postedHoursAgo = 3,
                sourcePlatform = "Renault Nissan Official"
            ),
            MechJobPost(
                id = "TN-ZF-04",
                title = "E-Mobility Drivetrain & Gearbox Testing Engineer",
                company = "ZF Group India",
                tier = CompanyTier.TIER_1,
                domain = "Testing & Validation",
                plantLocation = "Coimbatore / Chennai Tech Center",
                minExpYears = 2,
                maxExpYears = 6,
                description = "Test rig validation of EV reduction gearboxes and commercial transmissions. Sensor instrumentation and CANalyzer data logging.",
                keySkills = listOf("CANoe", "Test Bench", "Drivetrain", "Gearbox Inspection"),
                applyUrl = "https://www.zf.com/mobile/en/careers/careers.html",
                postedHoursAgo = 4,
                sourcePlatform = "ZF Official ATS"
            ),
            MechJobPost(
                id = "TN-STELLANTIS-05",
                title = "Vehicle Interior Trim CAD Engineer (CATIA V5)",
                company = "Stellantis (Citroën / Jeep)",
                tier = CompanyTier.OEM,
                domain = "CAD & Design",
                plantLocation = "Thiruvallur / Chennai",
                minExpYears = 3,
                maxExpYears = 8,
                description = "Plastic injection molding part design for cockpit and door trim modules. Tooling feasibility and DFM review.",
                keySkills = listOf("CATIA V5", "Plastic Injection", "DFM", "Interiors", "Tooling"),
                applyUrl = "https://www.stellantis.com/en/careers",
                postedHoursAgo = 5,
                sourcePlatform = "Stellantis Portal"
            ),
            MechJobPost(
                id = "TN-RANE-06",
                title = "Steering Gear & Hydraulic Valve QA Engineer",
                company = "Rane Group (Rane NSK / Rane TRW)",
                tier = CompanyTier.INDIAN_TIER_1,
                domain = "QA & Inspection",
                plantLocation = "Velachery / Sriperumbudur",
                minExpYears = 1,
                maxExpYears = 4,
                description = "APQP/PPAP documentation for power steering assemblies. Incoming casting quality, leak testing, and non-destructive testing.",
                keySkills = listOf("APQP / PPAP", "NDT Level 2", "Hydraulics", "ISO/IATF 16949"),
                applyUrl = "https://ranegroup.com/careers/",
                postedHoursAgo = 6,
                sourcePlatform = "Rane Careers"
            ),
            MechJobPost(
                id = "TN-VALEO-07",
                title = "Thermal Systems & Wiper Motor Tooling Engineer",
                company = "Valeo India",
                tier = CompanyTier.TIER_1,
                domain = "Tooling & Manufacturing",
                plantLocation = "Navalur / Vallam, Chennai",
                minExpYears = 2,
                maxExpYears = 5,
                description = "Press tools and progressive die maintenance for automotive thermal heat exchangers and wiper assemblies.",
                keySkills = listOf("Press Tools", "Progressive Dies", "Stamping", "AutoCAD"),
                applyUrl = "https://www.valeo.com/en/careers/",
                postedHoursAgo = 9,
                sourcePlatform = "Valeo Portal"
            ),
            MechJobPost(
                id = "TN-VINFAST-08",
                title = "Battery Pack Enclosure & Stamping Specialist",
                company = "VinFast Auto India",
                tier = CompanyTier.EV_MFG,
                domain = "Production & Stamping",
                plantLocation = "Thoothukudi Plant / Chennai",
                minExpYears = 2,
                maxExpYears = 7,
                description = "Setting up greenfield automated press lines and robotic laser welding cells for electric vehicle battery enclosures.",
                keySkills = listOf("Robotic Welding", "Sheet Metal Stamping", "EV Battery Pack", "Greenfield"),
                applyUrl = "https://vinfastauto.com/en/careers",
                postedHoursAgo = 3,
                sourcePlatform = "VinFast Official"
            ),
            MechJobPost(
                id = "TN-YAMAHA-09",
                title = "Engine Assembly & Dyno Testing Engineer",
                company = "India Yamaha Motor",
                tier = CompanyTier.OEM,
                domain = "Testing & Validation",
                plantLocation = "Kanchipuram, Tamil Nadu",
                minExpYears = 1,
                maxExpYears = 4,
                description = "Chassis dynamometer testing, emission compliance verification, and assembly line fault troubleshooting.",
                keySkills = listOf("Dynamometer", "Emission Testing", "Engine Tuning", "Kaizen"),
                applyUrl = "https://www.yamaha-motor-india.com/careers.html",
                postedHoursAgo = 18,
                sourcePlatform = "Yamaha Careers"
            ),
            MechJobPost(
                id = "TN-SIMPSON-10",
                title = "Diesel Engine Foundry & Metallurgy Engineer",
                company = "Simpson & Co. Ltd (Amalgamations)",
                tier = CompanyTier.INDIAN_TIER_1,
                domain = "Foundry & Materials",
                plantLocation = "Sembiam, Chennai",
                minExpYears = 3,
                maxExpYears = 8,
                description = "Supervision of gray iron and ductile iron melting furnaces for high-torque industrial diesel engines.",
                keySkills = listOf("Foundry / Casting", "Metallography", "Spectrometer", "Heat Treatment"),
                applyUrl = "https://www.simpsons.co.in/careers/",
                postedHoursAgo = 26,
                sourcePlatform = "Simpson Official"
            )
        )
    }
}
