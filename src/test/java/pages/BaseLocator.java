package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

abstract class BaseLocator<TPage> extends BaseWait<TPage> {

    BaseLocator(Page page) {
        super(page);
    }

    protected Locator button(String text) {

        return getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator exactButton(String text) {

        return getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(text).setExact(true));
    }

    protected Locator link(String text) {

        return getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator exactLink(String text) {

        return getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(text).setExact(true));
    }

    protected Locator heading(String text) {

        return getPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator exactHeading(String text) {

        return getPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(text).setExact(true));
    }

    protected Locator label(String text) {

        return getPage().getByLabel(text);
    }

    protected Locator text(String text) {

        return getPage().getByText(text);
    }

    protected Locator exactText(String text) {

        return getPage().getByText(text, new Page.GetByTextOptions().setExact(true));
    }

    protected Locator radio(String text) {

        return getPage().getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator radio() {

        return getPage().getByRole(AriaRole.RADIO);
    }

    protected Locator alert() {

        return getPage().getByRole(AriaRole.ALERT);
    }

    protected Locator image(String text) {

        return getPage().getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator dialog() {

        return getPage().getByRole(AriaRole.DIALOG);
    }

    protected Locator buttonInBanner(String text) {

        return getPage().getByRole(AriaRole.BANNER)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName(text));
    }

    protected Locator placeholder(String text) {

        return getPage().getByPlaceholder(text);
    }

    protected Locator locator(String css) {

        return getPage().locator(css);
    }

    protected Locator textbox() {

        return getPage().getByRole(AriaRole.TEXTBOX);
    }

    protected Locator checkbox(int number) {

        return getPage().getByRole(AriaRole.CHECKBOX).nth(number);
    }

    protected List<Locator> allCheckboxes() {
        Locator checkbox = getPage().getByRole(AriaRole.CHECKBOX);

        return getList(checkbox);
    }

    protected List<Locator> allCheckboxes(String css) {

        return getList(locator(css));
    }

    protected List<Locator> allButtons(Locator locator) {

        return getList(locator);
    }

    protected List<Locator> allRadioButtons() {

        return getList(radio());
    }

    protected List<Locator> allItems(String css) {

        return getList(locator(css));
    }

    protected List<Locator> allItems(Locator locator) {

        return getList(locator);
    }

    private List<Locator> getList(Locator locator) {
        while(!locator.first().isVisible() && !locator.last().isVisible()) {
            locator.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            locator.last().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
            locator.last().scrollIntoViewIfNeeded();
            locator.last().focus();
        }

        return locator.all();
    }
}