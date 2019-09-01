package pl.mm.documentArchive.service;

public interface IBasicDocumentArchiverService<T> {

	T findByUuid(String uuid, Class<T> clazz);

}
