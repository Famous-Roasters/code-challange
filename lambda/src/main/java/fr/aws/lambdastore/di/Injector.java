package fr.aws.lambdastore.di;

public class Injector {
    public static AppComponent getInjector() {
        return DaggerAppComponent.builder().build();
    }

}
