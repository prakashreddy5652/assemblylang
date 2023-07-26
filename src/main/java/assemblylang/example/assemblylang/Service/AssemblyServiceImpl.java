package assemblylang.example.assemblylang.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assemblylang.example.assemblylang.entity.AssemblyPrograms;
import assemblylang.example.assemblylang.repository.AssemblyRepository;

@Service
public class AssemblyServiceImpl implements AssemblyService {
	
	@Autowired
	private AssemblyRepository assemblyRepository;

	@Override
	public AssemblyPrograms saveRegister(AssemblyPrograms registerRecord) {
		return assemblyRepository.save(registerRecord);
	}

	@Override
	public List<AssemblyPrograms> fetchAssemblyprgrams(AssemblyPrograms record, Long id) {
		return assemblyRepository.findAll();
	}

}
