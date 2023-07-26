package assemblylang.example.assemblylang.Service;

import java.util.List;

import assemblylang.example.assemblylang.entity.AssemblyPrograms;

public interface AssemblyService {
	
	AssemblyPrograms saveRegister(AssemblyPrograms registerRecord);
	
	List<AssemblyPrograms> fetchAssemblyprgrams(AssemblyPrograms record, Long id);

}
