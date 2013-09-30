from lxml import etree
from datetime import datetime
from io import BytesIO


class BankAccount(object):
    """ A simple class for manipulating bank data
    """

    def parse_input_format(self, initial_data):
        """ Get relevant profile data from a string

        @param initial_data: multiline data string, that contains lines of the 
                             format `keyword: data` 
        @return: tuple that contains the following strings (bank account prefix, 
                 bank account suffix, owner name, expiration date)
        """
        if not initial_data:
            return

        bank_account, owner_name, owner_surname, expiration = initial_data.splitlines()
        account_data = bank_account.split(":")[1]
        account_prefix, account_suffix = account_data.strip().split("/")

        name = owner_name.split(":")[1]
        surname = owner_surname.split(":")[1]
        owner = "{0} {1}".format(name.strip(), surname.strip())

        date_ = expiration.split(":")[1]
        month, year = date_.split("/")
        exp_date = datetime.strptime("{0}/{1}".format(month.title().strip(), year.strip()), "%b/%Y")
        exp_date_str = exp_date.strftime("%d/%Y")
        return account_prefix, account_suffix, owner, exp_date_str

    def create_xml_profile(self, profile_data):
        """ Create an XML object that contains the input profile data

        @param profile_data: tuple that contains the following strings (bank 
                             account prefix, bank account suffix, owner name, 
                             expiration date)
        @return: string buffer that contains unicode representation of the XML 
                 profile tree
        """
        account_prefix, account_suffix, owner, exp_date_str = profile_data
        bank_account = etree.Element('bankAccount')
        account_prefix_element = etree.Element('accountPrefix')
        account_prefix_element.text = account_prefix
        bank_account.append(account_prefix_element)
        account_suffix_element = etree.Element('accountSuffix')
        account_suffix_element.text = account_suffix
        bank_account.append(account_suffix_element)
        owner_element = etree.Element('owner')
        owner_element.text = owner
        bank_account.append(owner_element)
        exp_element = etree.Element('exp')
        exp_element.text = exp_date_str
        bank_account.append(exp_element)

        str_tree = etree.tostring(bank_account, pretty_print=True)
        return BytesIO(str_tree)


if __name__ == "__main__":
    initial_data = """bank_account: XXXX-XXXX/XXXX
owner_name: NAME
owner_surname: SURNAME
expiration: JAN/2020"""

    bank_account = BankAccount()
    profile_data =  bank_account.parse_input_format(initial_data)
    xml_profile = bank_account.create_xml_profile(profile_data)
    print "{0}\n{1}\n\n{2}\n{3}".format("FROM:", initial_data, "TO:", xml_profile.read())
