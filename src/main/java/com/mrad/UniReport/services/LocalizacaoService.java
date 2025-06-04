package com.mrad.UniReport.services;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mrad.UniReport.entities.Localizacao;
import com.mrad.UniReport.repositories.LocalizacaoRepository;
import com.mrad.UniReport.services.exceptions.ResourceNotFoundException;

@Service
public class LocalizacaoService {
	@Autowired
	private LocalizacaoRepository repository;
	
	public List<Localizacao> findAll(){
		return repository.findAll();
	}
	
	public Localizacao findById(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Localização não encontrada com o ID " + id));
	}
	
	public Localizacao insert(Localizacao obj) {
        Localizacao savedLocalizacao = repository.save(obj);
		String qrCodeBase64 = generateQRCodeBase64WithLocationId(savedLocalizacao);
        savedLocalizacao.setQrCode(qrCodeBase64);
		return repository.save(obj);
	}
	
	 private String generateQRCodeBase64WithLocationId(Localizacao localizacao) {
	        String qrContent = String.format("{\"locationId\": %d}", localizacao.getId());
	        try {
	            Map<EncodeHintType, Object> hints = new HashMap<>();
	            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

	            BitMatrix bitMatrix = new MultiFormatWriter().encode(
	                    qrContent,
	                    BarcodeFormat.QR_CODE,
	                    250, 250, hints);

	            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	            byte[] pngData = pngOutputStream.toByteArray();
	            return Base64.getEncoder().encodeToString(pngData);
	        } catch (Exception e) {
	            throw new RuntimeException("Erro ao gerar QR Code para localização ID: " + localizacao.getId(), e);
	        }
	    }
	
}
