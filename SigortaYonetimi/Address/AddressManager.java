package SigortaYonetimi.Address;

import SigortaYonetimi.User;

public class AddressManager {
    public static void addAddress(User user, Address address) {
        user.getAddress().add(address);
    }

    public static void removeAddress(User user, Address address) {
        user.getAddress().remove(address);
    }
}
