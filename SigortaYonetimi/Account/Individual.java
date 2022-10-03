package SigortaYonetimi.Account;

import SigortaYonetimi.Address.AddressManager;
import SigortaYonetimi.Address.HomeAddress;
import SigortaYonetimi.User;

import java.util.ArrayList;

public class Individual extends Account{
    public Individual() {
        this.setStatus(AuthenticationStatus.FAIL);
        this.setInsurances(new ArrayList<>());
    }

    public Individual(User user) {
        this();
        this.setUser(user);
    }

    @Override
    public void addAddress(String address) {
        AddressManager.addAddress(this.getUser(), new HomeAddress(address));
    }
}