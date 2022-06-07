package no.kartveit.service

import no.kartveit.model.VesselData
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.net.HttpURLConnection
import java.net.URL

class VesselDataService {
    fun getVesselData(): List<VesselData> {

        val fartoyDataForsteEierUrl =
            URL("https://fiskeridir.no/Tall-og-analyse/AApne-data/AApne-datasett/Fartoey-eier-og-fisketillatelser/_/attachment/download/579dd5d6-e9fa-4928-9473-7b9fd0bc2767:afdaafbb31406512df8cc45be1ce4354927d85f2/fart%C3%B8y-eier-f%C3%B8rste-ledd.xlsx").openConnection() as HttpURLConnection

        val fartoyDataForsteEierKomplettWorkbook: Workbook = XSSFWorkbook(fartoyDataForsteEierUrl.inputStream)
        val fartoyDataForsteEierKomplettSheet2019: Sheet = fartoyDataForsteEierKomplettWorkbook.getSheetAt(2)


        return fartoyDataForsteEierKomplettSheet2019
            .filter { !it.getCell(0).stringCellValue.toString().matches(Regex("Data pr.")) }
            .filter { !it.getCell(0).stringCellValue.toString().matches(Regex("")) }
            .map { row ->
                VesselData(
                    countyNumber = row.getCell(1).stringCellValue.toString(),
                    municipalityNumber = row.getCell(3).stringCellValue.toString(),
                    vesselId = row.getCell(5).stringCellValue.toString(),
                    vesselName = row.getCell(8).stringCellValue.toString(),
                    municipalityName = row.getCell(4).stringCellValue.toString(),

                    )
            }
    }
}