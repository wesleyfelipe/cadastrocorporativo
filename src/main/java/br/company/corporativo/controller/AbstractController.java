package br.company.corporativo.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public abstract class AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected static final String CONTENT_TYPE_PDF = "application/pdf";

	protected void showInfoMessageRegistroSalvo(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	protected void showInfoMessageRegistroSalvo() {
		showInfoMessageRegistroSalvo( "Registro salvo com sucesso");
	}
	
	protected void showInfoMessageRegistroExcluido() {
		showInfoMessageRegistroSalvo( "Registro excluído com sucesso");
	}
	
	@SuppressWarnings("deprecation")
	protected void hideModal(String widgetvar) {
		RequestContext.getCurrentInstance().execute("PF('"+widgetvar+"').hide()");
	}
	
	public InputStream gerarRelatorioPdf(String caminho, FacesContext context, JRBeanCollectionDataSource dt,
			Map<String, Object> parameters) throws JRException, FileNotFoundException {
		
		if(parameters == null) {
			parameters = new HashMap<>();
		}

		File arquivo = new File(context.getExternalContext().getRealPath(caminho));
		FileInputStream arquivoStream = new FileInputStream(arquivo);


		JasperReport pathReport = JasperCompileManager.compileReport(arquivoStream);
		JasperPrint preencher = JasperFillManager.fillReport(pathReport, parameters,
				(dt != null) ? dt : new JREmptyDataSource());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		exporter.setExporterInput(new SimpleExporterInput(preencher));
		exporter.exportReport();

		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}

}
