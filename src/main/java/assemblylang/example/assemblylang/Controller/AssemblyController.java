package assemblylang.example.assemblylang.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assemblylang.example.assemblylang.Config.AssemblyInterpreter;
import assemblylang.example.assemblylang.Service.AssemblyService;
import assemblylang.example.assemblylang.entity.AssemblyPrograms;
import assemblylang.example.assemblylang.model.RegisterForm;

@RestController
public class AssemblyController {
	
    @Autowired
    private AssemblyInterpreter interpreter;
    
    @Autowired
    private AssemblyService assemblyService;

    @PostMapping("/execute")
    public ResponseEntity<String> executeAssemblyProgram(@RequestBody String assemblyCode) {
    	List<RegisterForm> registerForm = new ArrayList();
    	AssemblyPrograms assemblyEntity = new AssemblyPrograms();
    	
        try {
            String[] instructions = assemblyCode.split("\n");
            registerForm = interpreter.execute(instructions);
            for (RegisterForm registerFormInpout : registerForm) {
            	assemblyEntity.setProgram(registerFormInpout.getProgram());
            	assemblyEntity.setReg1(registerFormInpout.getRegOne());
            	assemblyEntity.setReg2(registerFormInpout.getRegTwo());
            	assemblyEntity.setSuccess(true);
            	assemblyService.saveRegister(assemblyEntity);
			}
            return ResponseEntity.ok("Program executed successfully.");
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/show-reg")
    public ResponseEntity<?> showRegisterValue(@RequestParam String id, 
    		@RequestBody String register) {
    	List<RegisterForm> registerForm = new ArrayList();
    	AssemblyPrograms assemblyEntityInput = new AssemblyPrograms();
    	List<AssemblyPrograms> assemblyEntityOutput =null;
    	RegisterForm registerFormRecord = new RegisterForm();
    	
        try {
        	String[] instructions = register.split("\n");
            registerForm = interpreter.execute(instructions);
            registerFormRecord = registerForm.get(0);
//            assemblyEntityInput.setId(Long.parseLong(id));
            assemblyEntityInput.setProgram(registerFormRecord.getProgram());
            assemblyEntityInput.setReg1(registerFormRecord.getRegOne());
            assemblyEntityInput.setReg2(registerFormRecord.getRegTwo());
            assemblyEntityInput.setSuccess(true);
            assemblyEntityOutput = assemblyService.fetchAssemblyprgrams(assemblyEntityInput, Long.parseLong(id));
            return new ResponseEntity<List<AssemblyPrograms>>(assemblyEntityOutput, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}