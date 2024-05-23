package com.example.photoapp.services;

import com.example.photoapp.models.Photo;
import com.example.photoapp.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Optional<Photo> getPhotoById(Long id) {
        return photoRepository.findById(id);
    }

    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo updatePhoto(Long id, Photo updatedPhoto) {
        return photoRepository.findById(id).map(photo -> {
            photo.setName(updatedPhoto.getName());
            photo.setUrl(updatedPhoto.getUrl());
            return photoRepository.save(photo);
        }).orElseGet(() -> {
            updatedPhoto.setId(id);
            return photoRepository.save(updatedPhoto);
        });
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
