document.addEventListener('DOMContentLoaded', function () {
    // Function to handle form toggling
    function handleToggleForm(toggleId, formSectionId, shippingId) {
        const switcher = document.getElementById(toggleId);
        const formSection = document.getElementById(formSectionId);
        const shipping = document.getElementById(shippingId);

        if (switcher && formSection && shipping) {
            switcher.addEventListener('change', function () {
                formSection.classList.toggle('hidden-visibility', !this.checked);
                shipping.classList.toggle('hidden', !this.checked);
            });
        }
    }

    // Initialize form toggle handlers
    handleToggleForm('toggleForm', 'form-section','shipping1');
    handleToggleForm('toggleForm2', 'form-section-2','shipping2');
    handleToggleForm('toggleForm3', 'form-section-3','shipping3');
    handleToggleForm('toggleForm4', 'form-section-4','shipping4');
    handleToggleForm('toggleForm5', 'form-section-5','shipping5');

    // Load saved customer data for each tab
    function loadCustomerData(tabId) {
        const customerNameSpan = document.querySelector(`#customer-name-${tabId} .font-semibold`);
        const customerEmailSpan = document.querySelector(`#customer-email-${tabId} .font-semibold`);
        const customerPhoneSpan = document.querySelector(`#customer-phone-${tabId} .font-semibold`);
        const customerIdInput = document.querySelector(`#customer-id-${tabId}`);

        const savedCustomer = JSON.parse(localStorage.getItem(`selectedCustomer-${tabId}`));
        if (savedCustomer) {
            customerIdInput.value = savedCustomer.id;
            customerNameSpan.textContent = savedCustomer.name;
            customerEmailSpan.textContent = savedCustomer.email;
            customerPhoneSpan.textContent = savedCustomer.phone;

            document.querySelector(`#customer-name-${tabId}`).style.display = 'flex';
            document.querySelector(`#customer-email-${tabId}`).style.display = 'flex';
            document.querySelector(`#customer-phone-${tabId}`).style.display = 'flex';
        } else {
            // Hide customer info sections if no customer is saved
            document.querySelector(`#customer-name-${tabId}`).style.display = 'none';
            document.querySelector(`#customer-email-${tabId}`).style.display = 'none';
            document.querySelector(`#customer-phone-${tabId}`).style.display = 'none';
        }
    }

    // Load customer data for all tabs
    for (let i = 1; i <= 5; i++) {
        loadCustomerData(i);
    }

    function activateTab(tabId) {
        const tabButtons = document.querySelectorAll('.tab-nav');
        const tabContents = document.querySelectorAll('.tab-pane');

        tabButtons.forEach(button => {
            if (button.getAttribute('data-bs-target') === `#${tabId}`) {
                button.classList.add('active');
                button.setAttribute('aria-selected', 'true');
            } else {
                button.classList.remove('active');
                button.setAttribute('aria-selected', 'false');
            }
        });

        tabContents.forEach(content => {
            if (content.id === tabId) {
                content.classList.add('active', 'show');
            } else {
                content.classList.remove('active', 'show');
            }
        });
    }

    // Event listener to save the active tab in localStorage
    document.querySelectorAll('.tab-nav').forEach(button => {
        button.addEventListener('click', function () {
            const tabId = this.getAttribute('data-bs-target').substring(1); // Remove the '#' from the ID
            localStorage.setItem('activeTab', tabId);
        });
    });

    // Load the active tab from localStorage if it exists
    const savedTab = localStorage.getItem('activeTab');
    if (savedTab) {
        activateTab(savedTab);
    } else {
        // If no tab is saved, default to the first tab
        activateTab('tab1');
    }

});

function clearSelectedCustomer(tabId) {
    // Remove customer info from localStorage for the specific tab
    localStorage.removeItem(`selectedCustomer-${tabId}`);

    // Define the customer info container based on the tabId
    const customerInfoContainer = document.getElementById(`customer-info-${tabId}`);

    if (customerInfoContainer) {
        // Clear the content of spans
        customerInfoContainer.querySelector(`#customer-id-${tabId}`).value = '';
        customerInfoContainer.querySelector(`#customer-name-${tabId} .font-semibold`).textContent = '';
        customerInfoContainer.querySelector(`#customer-email-${tabId} .font-semibold`).textContent = '';
        customerInfoContainer.querySelector(`#customer-phone-${tabId} .font-semibold`).textContent = '';

        // Hide customer info section
        customerInfoContainer.querySelector(`#customer-name-${tabId}`).style.display = 'none';
        customerInfoContainer.querySelector(`#customer-email-${tabId}`).style.display = 'none';
        customerInfoContainer.querySelector(`#customer-phone-${tabId}`).style.display = 'none';
    }
}

function selectCustomer(button, tabId) {
    const khachHangId = button.getAttribute('data-id');
    const hoTen = button.getAttribute('data-ho-ten');
    const email = button.getAttribute('data-email');
    const sdt = button.getAttribute('data-sdt');

    // Define the customer info container based on the tabId
    const customerInfoContainer = document.getElementById(`customer-info-${tabId}`);

    if (customerInfoContainer) {
        // Update the content of spans
        customerInfoContainer.querySelector(`#customer-id-${tabId}`).value = khachHangId;
        customerInfoContainer.querySelector(`#customer-name-${tabId} .font-semibold`).textContent = hoTen;
        customerInfoContainer.querySelector(`#customer-email-${tabId} .font-semibold`).textContent = email;
        customerInfoContainer.querySelector(`#customer-phone-${tabId} .font-semibold`).textContent = sdt;

        // Show customer info section
        customerInfoContainer.querySelector(`#customer-name-${tabId}`).style.display = 'flex';
        customerInfoContainer.querySelector(`#customer-email-${tabId}`).style.display = 'flex';
        customerInfoContainer.querySelector(`#customer-phone-${tabId}`).style.display = 'flex';

        // Save customer info to localStorage
        const customer = {id: khachHangId, name: hoTen, email: email, phone: sdt};
        localStorage.setItem(`selectedCustomer-${tabId}`, JSON.stringify(customer));

        // Hide the modal
        const modal = document.getElementById(`dialogKhachHang${tabId}`);
        if (modal) {
            const modalInstance = bootstrap.Modal.getInstance(modal);
            if (modalInstance) {
                modalInstance.hide();
            }
        }
    }

}
