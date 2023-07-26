package assemblylang.example.assemblylang.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assemblylang.example.assemblylang.Service.AssemblyService;
import assemblylang.example.assemblylang.model.RegisterForm;

@Service
public class AssemblyInterpreter {
    private int reg1;
    private int reg2;

    @Autowired
    AssemblyService assemblyService;
    
    
    public AssemblyInterpreter() {
        reg1 = 0;
        reg2 = 0;
    }

    public List<RegisterForm> execute(String[] instructions) {
    	List<RegisterForm> registerFormRecord = new ArrayList();
        for (String instruction : instructions) {
        	RegisterForm registerForm = null;
            String[] parts = instruction.trim().split("\\s+|,");
            String command = parts[0];
            
            if ("MV".equals(command)) {
                String register = parts[1];
                int value = Integer.parseInt(parts[2].substring(1));
                registerForm =  move(register, value);
                registerFormRecord.add(registerForm);
            } else if ("ADD".equals(command)) {
                String register = parts[1];
                if (parts[2].charAt(0) == '#') {
                    int value = Integer.parseInt(parts[2].substring(1));
                    registerForm = add(register, value);
                    registerFormRecord.add(registerForm);
                } else {
                    String sourceRegister = parts[2];
                    registerForm = add(register, getRegisterValue(sourceRegister));
                    registerFormRecord.add(registerForm);
                }
            } else if ("SHOW".equals(command)) {
                String register = parts[1];
                registerForm = show(register);
                registerFormRecord.add(registerForm);
            } else {
                throw new IllegalArgumentException("Invalid command: " + command);
            }

        }
		return registerFormRecord;
    }

    private RegisterForm move(String register, int value) {
    	RegisterForm registerForm = new RegisterForm();
        if (register.equals("REG1")) {
            reg1 = value;
            registerForm.setRegOne(reg1);
        } else if (register.equals("REG2")) {
            reg2 = value;
            registerForm.setRegTwo(reg2);
        } else {
            throw new IllegalArgumentException("Invalid register: " + register);
        }
        registerForm.setProgram("MOVE");
		return registerForm;
    }

    private RegisterForm add(String register, int value) {
    	RegisterForm registerForm = new RegisterForm();
        if (register.equals("REG1")) {
            reg1 += value;
            registerForm.setRegOne(reg1);
        } else if (register.equals("REG2")) {
            reg2 += value;
            registerForm.setRegTwo(reg2);
        } else {
            throw new IllegalArgumentException("Invalid register: " + register);
        }
        registerForm.setProgram("ADD");
		return registerForm;
    }

    private int getRegisterValue(String register) {
        if (register.equals("REG1")) {
            return reg1;
        } else if (register.equals("REG2")) {
            return reg2;
        } else {
            throw new IllegalArgumentException("Invalid register: " + register);
        }
    }

    public RegisterForm show(String register) {
    	RegisterForm registerForm = new RegisterForm();
        if (register.equals("REG1")) {
        	registerForm.setRegOne(reg1);
            System.out.println("REG1: " + reg1);
        } else if (register.equals("REG2")) {
            System.out.println("REG2: " + reg2);
            registerForm.setRegTwo(reg2);
        } else {
            throw new IllegalArgumentException("Invalid register: " + register);
        }
        registerForm.setProgram("SHOW");
		return registerForm;
    }
}