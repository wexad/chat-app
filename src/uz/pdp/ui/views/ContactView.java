package uz.pdp.ui.views;

import uz.pdp.backend.model.chat.Chats;
import uz.pdp.backend.model.contact.Contacts;
import uz.pdp.backend.service.chat_service.ChatService;
import uz.pdp.backend.service.chat_service.ChatServiceImpl;
import uz.pdp.backend.service.contact_service.ContactService;
import uz.pdp.backend.service.contact_service.ContactServiceImpl;
import uz.pdp.backend.service.user_service.UserService;
import uz.pdp.backend.service.user_service.UserServiceImpl;
import uz.pdp.ui.Main;
import uz.pdp.ui.utils.Input;
import uz.pdp.ui.utils.Message;

public class ContactView {
    static UserService userService = UserServiceImpl.getInstance();
    static ContactService contactService = ContactServiceImpl.getInstance();
    static ChatService chatService = ChatServiceImpl.getInstance();

    public static void addContact() {
        String number = Input.inputStr("Enter number : ");

        if (number.equals(Main.curUser.getNumber())) {
            System.out.println("It is your number idiot! ");
            return;
        }

        String contactId = userService.getUserByNumber(number);

        if (contactId == null) {
            System.out.println("There is not user with such number! Try again ? 1 yes / 0 no");

            if (Input.inputInt("Choice : ") == 1) {
                addContact();
            }
        } else {
            if (contactService.isExist(Main.curUser.getId(), contactId)) {
                Message.failure();
                System.out.println("There is exist contact! ");
                return;
            }


            String name = Input.inputStr("Enter name : ");
            Contacts contacts = new Contacts(Main.curUser.getId(), contactId, name);

            if (contactService.add(contacts)) {
                Message.success();
                System.out.println("New contact created successfully! ");
                if (chatService.getChatOfUser(Main.curUser.getId()) == null) {
                    chatService.add(new Chats(Main.curUser.getId(), contactId));
                }
            }
        }

    }
}
