package id.taskapp.categoryservice.service.impl;

import id.taskapp.categoryservice.domain.Master;
import id.taskapp.categoryservice.repo.MasterRepository;
import id.taskapp.categoryservice.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements MasterService {

    @Autowired
    private MasterRepository masterRepository;

    @Override
    public List<Master> findAllMasters() {
        return masterRepository.findAll();
    }
}
