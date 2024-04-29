package hybernate.service;

import hybernate.entity.License;

import java.util.List;

public interface LicenseService {
    License saveLicense(License license);

    License findLicenseById(Long licenseId);

    List<License> getAllLicenses();

    String updateLicense(Long licenseId, License newLicenseName);

    String deleteLicenseById(Long licenseId);

    String cleanLicense();
}

