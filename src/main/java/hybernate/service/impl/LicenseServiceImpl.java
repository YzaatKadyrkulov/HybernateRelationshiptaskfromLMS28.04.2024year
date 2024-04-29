package hybernate.service.impl;

import hybernate.dao.LicenseDao;
import hybernate.dao.impl.LicenseDaoImpl;
import hybernate.entity.License;
import hybernate.service.LicenseService;

import java.util.List;

public class LicenseServiceImpl implements LicenseService {
    LicenseDao licenseDao = new LicenseDaoImpl();
    @Override
    public License saveLicense(License license) {
        return licenseDao.saveLicense(license);
    }

    @Override
    public License findLicenseById(Long licenseId) {
        return licenseDao.findLicenseById(licenseId);
    }

    @Override
    public List<License> getAllLicenses() {
        return licenseDao.getAllLicenses();
    }

    @Override
    public String updateLicense(Long licenseId, License newLicenseName) {
        return licenseDao.updateLicense(licenseId,newLicenseName);
    }

    @Override
    public String deleteLicenseById(Long licenseId) {
        return licenseDao.deleteLicenseById(licenseId);
    }

    @Override
    public String cleanLicense() {
        return licenseDao.cleanLicense();
    }
}
