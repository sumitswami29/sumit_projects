let display = document.getElementById('display');
        let currentInput = '';

        function appendToDisplay(value) {
            currentInput += value;
            display.textContent = currentInput;
        }

        function calculate() {
            try {
                currentInput = eval(currentInput).toString();
                display.textContent = currentInput;
            } catch (error) {
                display.textContent = 'Error';
            }
        }

        function clearDisplay() {
            currentInput = '';
            display.textContent = '0';
        }