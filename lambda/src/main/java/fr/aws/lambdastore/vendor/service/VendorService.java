package fr.aws.lambdastore.vendor.service;

import fr.aws.lambdastore.utils.HibernateDataSource;
import fr.aws.lambdastore.vendor.model.Vendor;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class VendorService {

    private static final String VENDOR_NAME = "vendorName";

    @Inject
    public VendorService() {}

    public static Vendor getVendorByName(String vendorName) {

        Vendor vendorToPass = new Vendor();

        HibernateDataSource<Vendor> dataSource = new HibernateDataSource<>();
        dataSource.prepareQuery("from Vendor v where v.name=:vendorName", vendorToPass);
        dataSource.getQuery().setParameter(VENDOR_NAME, vendorName);
        vendorToPass = dataSource.executeReadQuery();

        if(vendorToPass == null ){
            vendorToPass = new Vendor();
        }

        if ( vendorToPass.getId() == null) {
            vendorToPass.setName(vendorName);
            vendorToPass.setDescription(vendorName + " is created automatically");
            dataSource.executeWriteQueryWithFeedback(vendorToPass);
        }

        dataSource.close();
        return vendorToPass;
    }
}
